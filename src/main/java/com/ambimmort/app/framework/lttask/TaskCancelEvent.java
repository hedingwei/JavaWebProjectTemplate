package com.ambimmort.app.framework.lttask;

/**
 * Created by hedingwei on 6/18/15.
 */
public class TaskCancelEvent {

    private String reason;
    private String canceller;

    private boolean isCancelledByParent = false;

    private boolean isCancelledBySubtask = false;

    private String cancelSubtaskId = "";



    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCanceller() {
        return canceller;
    }

    public void setCanceller(String canceller) {
        this.canceller = canceller;
    }

    public boolean isCancelledByParent() {
        return isCancelledByParent;
    }

    public void setCancelledByParent(boolean isCancelledByParent) {
        this.isCancelledByParent = isCancelledByParent;
    }

    public boolean isCancelledBySubtask() {
        return isCancelledBySubtask;
    }

    public void setCancelledBySubtask(boolean isCancelledBySubtask) {
        this.isCancelledBySubtask = isCancelledBySubtask;
    }

    public String getCancelSubtaskId() {
        return cancelSubtaskId;
    }

    public void setCancelSubtaskId(String cancelSubtaskId) {
        this.cancelSubtaskId = cancelSubtaskId;
    }
}
