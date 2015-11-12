/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ambimmort.nisp3.service.def;

import com.ambimmort.nisp3.model.ui.f.logs.LogBean;
import com.ambimmort.nisp3.service.def.IModular.Action;
import com.ambimmort.nisp3.service.def.IModular.Modular;
import com.ambimmort.nisp3.service.def.IModular.Result;
import com.ambimmort.nisp3.service.def.IModular.SubModular;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by qinxiaoyao on 6/17/15.
 */
public interface ILogService {



    /**
     * 普通日志记录接口

     * @param modular       系统模块名
     * @param submodular    系统子模块名
     * @param action        操作动作：添加、修改、导入、导出、删除
     * @param result        执行结果：成功、失败
     * @param newDetail        执行细节：一段描述性语言描述当前的执行操作和细节
     * @param obj           执行对象：一般直接填入表单对象

     */
    public void recordLogs(Modular modular, SubModular submodular, Action action, Result result, Object obj, String newDetail, HttpServletRequest request)  throws Exception ;

    /**
     * 修改类日志记录接口

     * @param modular       系统模块名
     * @param submodular    系统子模块名
     * @param action        操作动作：添加、修改、导入、导出、删除
     * @param result        执行结果：成功、失败
     * @param newDetail     新执行细节：一段描述性语言描述当前的执行操作和细节
     * @param oldDetail     旧执行细节：一段描述性语言描述当前的执行操作和细节   （方便新旧值对比，查看修改内容）
     * @param obj           执行对象：一般直接填入表单对象
     */
    public void recordUpdatedLogs(Modular modular, SubModular submodular, Action action, Result result, String newDetail, String oldDetail, Object obj, HttpServletRequest request)  throws Exception;

    /**
     * 操作失败类日志记录接口

     * @param modular       系统模块名
     * @param submodular    系统子模块名
     * @param action        操作动作：添加、修改、导入、导出、删除
     * @param result        执行结果：成功、失败
     * @param detail        执行细节：一段描述性语言描述当前的执行操作和细节
     * @param reason        执行失败原因
     * @param obj           执行对象：一般直接填入表单对象
     */
    public void recordErrorLogs(Modular modular, SubModular submodular, Action action, Result result, String detail, String reason, Object obj, HttpServletRequest request)  throws Exception;


    /**
     * 获取指定模块、时间段的日志记录
     * @param modular       系统模块名
     * @param startTime     起始时间
     * @param endTime       结束时间
     * @param startLine     起始行数
     * @param interval      从起始行数开始的记录数
     * @return
     * @throws Exception
     */
    public List<LogBean> listLogs(Modular modular, String startTime, String endTime, int startLine, int interval, HttpServletRequest request) throws Exception;

    /**
     * 获取当前指定模块、时间段的日志记录数
     * @param modular
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public int getTotalLogsNumber(Modular modular, String startTime, String endTime) throws Exception;

}