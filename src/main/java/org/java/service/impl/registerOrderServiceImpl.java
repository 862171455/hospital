package org.java.service.impl;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.logging.log4j.core.helpers.UUIDUtil;
import org.java.dao.registerOrderMapper;
import org.java.service.registerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.*;

/**
 * Created by duankun1997@qq.com on 2019/8/26 8:46
 */
@Service
public class registerOrderServiceImpl implements registerOrderService {
    @Autowired
    private registerOrderMapper registerOrderMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private TaskService taskservice;

    @Override
    @Transactional
    public List<Map<String, Object>> showTask(String user) {
        TaskQuery query = taskservice.createTaskQuery();
        query.taskAssignee(user);
        List<Task> tasklist = query.list();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Task t : tasklist) {
            String processInstanceId = t.getProcessInstanceId();
            Map<String, Object> map = registerOrderMapper.findProcessInstanceId(processInstanceId);
            map.put("taskId", t.getId());//任务id
            map.put("taskName", t.getName());//任务名称
            map.put("assignee", t.getAssignee());//任务办理人
            map.put("createtime", t.getCreateTime());//任务的开始时间
            map.put("taskDefKey", t.getTaskDefinitionKey());//任务执行到那个阶段
            list.add(map);
        }
        return list;

    }
    @Override
    public List<Map<String, Object>> showProcessInstance() {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        List<ProcessInstance> instance = query.list();
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for(ProcessInstance p:instance){
            String InstanceId = p.getProcessInstanceId();
            Map<String,Object> map = registerOrderMapper.findProcessInstanceId(InstanceId);
            map.put("processInstanceId", p.getProcessInstanceId());
            map.put("processDefinitionId", p.getProcessDefinitionId());
            map.put("activityId", p.getActivityId());//任务运行哪一个阶段
            list.add(map);
        }

        return list;
    }

    @Override
    public void submitOrder(String taskId) {
        taskservice.complete(taskId);
    }

    @Override
    public List<Map<String, Object>> showclaimTask(String userId) {
        System.out.println(userId);
        TaskQuery query = taskservice.createTaskQuery();
        query.taskCandidateUser(userId);
        List<Task> tasklist = query.list();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Task t : tasklist) {
            String processInstanceId = t.getProcessInstanceId();
            Map<String, Object> map = registerOrderMapper.findProcessInstanceId(processInstanceId);
            map.put("taskId", t.getId());//任务id
            map.put("taskName", t.getName());//任务名称
            map.put("assignee", t.getAssignee());//任务办理人
            map.put("createtime", t.getCreateTime());//任务的开始时间
            map.put("taskDefKey", t.getTaskDefinitionKey());//任务执行到那个阶段
            list.add(map);

        }
        return list;

    }

    @Override
    public void claimTask(String userId, String taskId) {
        TaskQuery query = taskservice.createTaskQuery();
        query.taskCandidateUser(userId);
        query.taskId(taskId);
        Task result = query.singleResult();
        if (result != null) {
            taskservice.claim(taskId, userId);
        }
    }

    public List<Map<String, Object>> showback(String userId) {
        TaskQuery query = taskservice.createTaskQuery();
        query.taskAssignee(userId);
        List<Task> tasklist = query.list();
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for(Task t:tasklist){
            String processInstanceId = t.getProcessInstanceId();
            Map<String, Object> map = registerOrderMapper.findProcessInstanceId(processInstanceId);
            map.put("taskId", t.getId());//任务id
            map.put("taskName",t.getName());//任务名称
            map.put("assignee", t.getAssignee());//任务办理人
            map.put("createtime",t.getCreateTime());//任务的开始时间
            map.put("taskDefKey",t.getTaskDefinitionKey());//任务执行到那个阶段
            list.add(map);



        }
        return list;
    }

    @Override
    public Map<String, Object> findOrder(String regId) {
        return registerOrderMapper.findOrder(regId);
    }

    @Transactional
    public void OrderSumit(Map<String, Object> map) {

        String key = map.get("defKey").toString();
        String val = map.get("status").toString();
        String eat = (String) map.get("eat");
        if(eat==null){
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put(key,val);
            String taskId = (String) map.get("taskId");
            taskservice.complete(taskId,variables);
        }else{
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("eat",eat);
            variables.put(key,val);
            String taskId = (String) map.get("taskId");
            taskservice.complete(taskId,variables);
        }

        String id = UUIDUtil.getTimeBasedUUID().toString();
        Date Createtime = new Date();
        map.put("id", id);
        map.put("Createtime", Createtime);
        registerOrderMapper.OrderSumit(map);
    }

    @Override
    public void storage(String taskId) {
        taskservice.complete(taskId);

    }

    @Override
    @Transactional
    public void registerOrder(Map map) {
            String taskId=(String) map.get("taskId");
            taskservice.complete(taskId);
            String id=UUIDUtil.getTimeBasedUUID().toString();
            Date Createtime=new Date();
            map.put("id",id);
            map.put("Createtime",Createtime);
            registerOrderMapper.doctor_med(map);
    }



    @Transactional
    public void detailsCheck(Map map) {
        String key = map.get("defKey").toString();
        String val="1";
        String vals = map.get("vals").toString();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(key,val);
        variables.put("detailsCheck",vals);

        String taskId = (String) map.get("taskId");
        taskservice.complete(taskId,variables);

        String id = UUIDUtil.getTimeBasedUUID().toString();
        Date Createtime = new Date();
        map.put("id", id);
        map.put("Createtime",Createtime);
        registerOrderMapper.detailsCheck(map);
    }
    @Transactional
    @Override
    public void checkOver(Map map) {
        String key = map.get("defKey").toString();
        String val = map.get("detailsCheckover").toString();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(key,val);
        String taskId = (String) map.get("taskId");
        taskservice.complete(taskId,variables);
        String id = UUIDUtil.getTimeBasedUUID().toString();
        Date Createtime = new Date();
        map.put("id", id);
        map.put("Createtime",Createtime);
        registerOrderMapper.checkOver(map);
    }
    @Transactional
    @Override
    public void cure(Map map) {
        String key = map.get("defKey").toString();
        String val="1";
        String vals = map.get("status").toString();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(key,val);
        variables.put("cure",vals);

        String taskId = (String) map.get("taskId");
        taskservice.complete(taskId,variables);

        String id = UUIDUtil.getTimeBasedUUID().toString();
        Date Createtime = new Date();
        map.put("id", id);
        map.put("Createtime",Createtime);
        registerOrderMapper.cure(map);
    }
    @Transactional
    @Override
    public void operation(Map map) {
        String key = map.get("defKey").toString();
        String val="1";
        String vals = map.get("vals").toString();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(key,val);
        variables.put("operationtype",vals);

        String taskId = (String) map.get("taskId");
        taskservice.complete(taskId,variables);

        String id = UUIDUtil.getTimeBasedUUID().toString();
        Date Createtime = new Date();
        map.put("id", id);
        map.put("Createtime",Createtime);
        registerOrderMapper.operation(map);
    }

    @Override
    @Transactional
    public void operationOver(Map map) {
        String key = map.get("defKey").toString();
        String val = map.get("operationover_status").toString();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(key,val);
        String taskId = (String) map.get("taskId");
        taskservice.complete(taskId,variables);
        String id = UUIDUtil.getTimeBasedUUID().toString();
        Date Createtime = new Date();
        map.put("id", id);
        map.put("Createtime",Createtime);
        registerOrderMapper.operationOver(map);
    }
}