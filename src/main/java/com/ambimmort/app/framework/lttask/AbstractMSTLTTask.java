package com.ambimmort.app.framework.lttask;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.*;

/**
 * Created by hedingwei on 6/17/15.
 */
public abstract class AbstractMSTLTTask extends AbstractTask implements Serializable {


    private int totalSteps = 0;

    private AbstractSubTask currentSubtask = null;

    private Integer[] currentSubtaskRange = null;

    private Integer currentSubtaskIndex = null;

    private List<TaskModel> subtaskModels = new ArrayList<>();

    private List<String> subtaskSequence = new ArrayList<>();

    private List<AbstractSubTask> subtasks = new ArrayList<>();

    private int subtaskStepPtr = 0;

    private int subtaskStepIndex = 0;


    private int currentStepInAll = 0;


    private List<Integer[]> ranges = new ArrayList<>();

    public int getCurrentStepInAll() {
        return currentStepInAll;
    }

    public int getTotalSteps() {
        return totalSteps;
    }

    public abstract void setSubTasks() throws Throwable;

    public void add(String id,AbstractSubTask subTask){
        subtaskSequence.add(id);
        subtasks.add(subTask);
        subTask.setParentTask(this);
        subTask.getModel().setId(id);
        subtaskModels.add(subTask.getModel());
        addObserver(subTask);

    }

    public TaskModel get(String id){
        return subtaskModels.get(subtaskSequence.indexOf(id));
    }


    public AbstractSubTask getSubtask(String id){
        return subtasks.get(subtaskSequence.indexOf(id));
    }

    @Override
    public void setModel(TaskModel model) {
        super.setModel(model);
    }



    @Override
    public void init(TaskModel model) throws Throwable{
        setSubTasks();
        if(!model.getContext().containsKey("subtaskModels")){
            model.getContext().put("subtaskModels",this.subtaskModels);
        }
        if(!model.getContext().containsKey("subtaskSequence")){
            model.getContext().put("subtaskSequence",this.subtaskSequence);
        }

        if (!subtaskSequence.isEmpty()){
            currentSubtaskIndex = 0;
            subtaskStepIndex = 0;
            currentSubtask = subtasks.get(currentSubtaskIndex);
            try {
                currentSubtask.getModel().setStartTime(System.currentTimeMillis());
                currentSubtask.init(currentSubtask.getModel());
            }catch (TaskCancelException be){
                currentSubtask.getModel().setStatus("cancelled");
                currentSubtask.getModel().setCancelled(true);
                currentSubtask.getModel().setCancelReason(be.getMessage());
                if(be.getEvent().isCancelledBySubtask()){
                    getModel().setCancelReason("子任务取消导致总体任务取消，被取消的子任务序号为:"+(currentSubtaskIndex+1));
                    cancel(be.getEvent());
                }
            }catch (Throwable throwable){
                throwable.printStackTrace();
                currentSubtask.onInitError(model, throwable);
            }
        }


    }

    public AbstractSubTask getSubTask(int index){
        return subtasks.get(index);
    }

    @Override
    public int getSteps() throws Throwable{
        AbstractSubTask subTask = null;
        int step = 0;
        for(int i=0;i<subtaskSequence.size();i++){

            subTask = subtasks.get(i);
            Integer[] range = new Integer[3];
            range[0] = totalSteps;
            step = subTask.getSteps();
            subTask.getModel().getContext().put("totalSteps",step);
            if(step==0){
                throw new StepZeroException();
            }
            totalSteps += step;
            range[1] = totalSteps;
            range[2] = totalSteps-1;
            ranges.add(range);
        }

        currentSubtaskRange = ranges.get(currentSubtaskIndex);
        getModel().getContext().put("totalSteps",totalSteps);
        return totalSteps;
    }




    @Override
    public void processStep(int i, TaskModel model) throws Throwable {

        currentStepInAll = i;

        if(currentSubtask !=null){


            if(currentSubtask.isCancelled()){
                setI(currentSubtaskRange[2]);
                i=currentSubtaskRange[2];

                currentSubtask.onCancelled(subtaskStepIndex, currentSubtask.getModel(), currentSubtask.getCancelReason());
            }else{
                try{
                    currentSubtask.processStep(subtaskStepIndex, currentSubtask.getModel());
                    subtaskStepPtr = (int)(((double)(i+1)- currentSubtaskRange[0])/(currentSubtaskRange[1]- currentSubtaskRange[0])*100);

//                    currentSubtask.setProgress(subtaskStepPtr);
                    doTimeEstimate(currentSubtask.getModel(),subtaskStepPtr,totalSteps);
                    currentSubtask.reportProgressByStep(((i+1)- currentSubtaskRange[0]));




                }catch (TaskCancelException be){
                    currentSubtask.getModel().setStatus("cancelled");
                    currentSubtask.getModel().setCancelled(true);
                    currentSubtask.getModel().setCancelReason(be.getMessage());
                    currentSubtask.onCancelled(subtaskStepIndex, currentSubtask.getModel(), be.getMessage());
                    if(be.getEvent().isCancelledBySubtask()){
                        getModel().setCancelReason("子任务取消导致总体任务取消，被取消的子任务序号为:"+(subtaskStepIndex+1));
                        cancel(be.getEvent());
                    }

                }catch (Throwable throwable){
                    throwable.printStackTrace();
                    currentSubtask.onError(subtaskStepIndex, currentSubtask.getModel(),throwable);
                }
            }
            if(isLastStep(i)){
                /**
                 * 此处两个方法中若发生异常抛出，则交由总任务的onError处理，而非子任务的onError处理
                 */

                try {
                    currentSubtask.onFinished(currentSubtask.getModel());
                }catch (Throwable throwable){
                    throwable.printStackTrace();
                    currentSubtask.getModel().setStatus("cancelled");
                    throw throwable;
                }

                try {

                    currentSubtask.onFinalize(currentSubtask.getModel());
                    currentSubtask.getModel().setStatus("success");
                }catch (Throwable throwable){
                    throwable.printStackTrace();
                    currentSubtask.getModel().setStatus("cancelled");
                    throw throwable;
                }

                currentSubtask.getModel().setStopTime(System.currentTimeMillis());


                //初始化下一个任务
                currentSubtaskIndex ++;
                subtaskStepIndex=0;
                if(currentSubtaskIndex<ranges.size()) {
                    currentSubtask = subtasks.get(currentSubtaskIndex);
                    currentSubtaskRange = ranges.get(currentSubtaskIndex);

                    try {
                        currentSubtask.getModel().setStartTime(System.currentTimeMillis());
                        currentSubtask.init(currentSubtask.getModel());
                    }catch (TaskCancelException be){
                        currentSubtask.getModel().setStatus("cancelled");
                        currentSubtask.getModel().setCancelled(true);
                        currentSubtask.getModel().setCancelReason(be.getMessage());
                        if(be.getEvent().isCancelledBySubtask()){
                            getModel().setCancelReason("子任务取消导致总体任务取消，被取消的子任务序号为:"+(currentSubtaskIndex));

                            cancel(be.getEvent());
                        }
                        throw be;
                    }catch (Throwable throwable){
                        throwable.printStackTrace();
                        currentSubtask.onInitError(model,throwable);
                    }
                }
            }
            subtaskStepIndex++;

        }

    }


    @Override
    public void onCancelled(int i, TaskModel model, String reason) throws Throwable {
        int index = dispatch(i);
        if(index>=0){
            AbstractSubTask current = getSubTask(dispatch(i));
            if(current!=null){
                current.onCancelled(i,current.getModel(),reason);
            }
        }

    }

    @Override
    public void onError(int i, TaskModel model, Throwable throwable) {
        int index = dispatch(i);
        if(index>=0) {
            AbstractSubTask current = getSubTask(dispatch(i));
            if (current != null) {
                current.onError(i, current.getModel(), throwable);
            }
        }
    }



    private int dispatch(int i){
        for(int j=0;j<ranges.size();j++){
            if(in(i, ranges.get(j))){
                return j;
            }
        }
        return -1;
    }


    private boolean in(int index,Integer[] range){
        return index>=range[0]&&index<range[1];
    }

    private boolean isLastStep(int index){
        if(index==currentSubtaskRange[2]){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public void parse(String jsonModel) {
        JSONObject obj = JSONObject.fromObject(jsonModel);
        JSONObject context = obj.getJSONObject("context");

        if(context.containsKey("subtaskModels")){
            JSONArray array = context.getJSONArray("subtaskModels");
            for(int i=0;i<array.size();i++){
                this.subtaskModels.add((TaskModel) JSONObject.toBean(array.getJSONObject(i),TaskModel.class));
            }

            array = context.getJSONArray("subtaskSequence");
            for(int i=0;i<array.size();i++){
                this.subtaskSequence.add(array.getString(i));
            }


            try {
                TaskModel model = (TaskModel) JSONObject.toBean(obj, TaskModel.class);
                model.getContext().put("subtaskModels",subtaskModels);
                model.getContext().put("subtaskSequence",subtaskSequence);
                setModel(model);
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }

        }
    }


}
