package org.java.service.impl;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.logging.log4j.core.helpers.UUIDUtil;
import org.java.dao.CreateOrderMapper;
import org.java.service.CreateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by duankun1997@qq.com on 2019/8/18 15:27
 */
@Service
public class CreateOrderServiceImpl implements CreateOrderService {
    @Autowired
    private CreateOrderMapper createOrderMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private TaskService taskservice;

    @Override
    public void createOrder(Map map) {
//任务1：启动流程实例
        //使用uuid产生一个值，既作为：流程实例的BusinessKey,同时，也作为业务表的主键，让它们之间建立关联
        String id = UUIDUtil.getTimeBasedUUID().toString();
        //指定ProcessDefinitionKey,用于启动流程实例
        String processDefinitionKey = "myProcess";

        //从map中，取得用户名，该用户即为流程实例的发起者
        String userId = map.get("userId").toString();
        //设置流程实例的发起者
        identityService.setAuthenticatedUserId(userId);

        //启动流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefinitionKey,id);


        //任务2 ： 向业务表中，增加一条业务数据
        map.put("createTime", new Date());//采购单创单时间
        map.put("id",id);//业务表的主键(它与processInstance的business的值相同)
        map.put("processInstanceId", instance.getProcessInstanceId());//流程实例的 id

        createOrderMapper.createOrder(map);
    }

    @Override
    public List<Map<String, Object>> showTask(String user) {
        TaskQuery query = taskservice.createTaskQuery();
        query.taskAssignee(user);
        List<Task> tasklist = query.list();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Task t : tasklist) {
            String processInstanceId = t.getProcessInstanceId();
            Map<String,Object> map = createOrderMapper.findProcessInstanceId(processInstanceId);
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
            Map<String,Object> map = createOrderMapper.findProcessInstanceId(InstanceId);
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
        TaskQuery query = taskservice.createTaskQuery();
        query.taskCandidateUser(userId);
        List<Task> tasklist = query.list();
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for(Task t:tasklist){
            String processInstanceId = t.getProcessInstanceId();
            Map<String, Object> map = createOrderMapper.findProcessInstanceId(processInstanceId);
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
    public void claimTask(String userId, String taskId) {
        TaskQuery query = taskservice.createTaskQuery();
        query.taskCandidateUser(userId);
        query.taskId(taskId);
        Task result = query.singleResult();
        if(result!=null){
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
            Map<String, Object> map = createOrderMapper.findProcessInstanceId(processInstanceId);
            map.put("taskId", t.getId());//任务id
            map.put("taskName",t.getName());//任务名称
            map.put("assignee", t.getAssignee());//任务办理人
            map.put("createtime",t.getCreateTime());//任务的开始时间
            map.put("taskDefKey",t.getTaskDefinitionKey());//任务执行到那个阶段
            list.add(map);



        }
        return list;
    }
    public Map<String, Object> findOrder(String OrderId) {

        return createOrderMapper.findOrder(OrderId);
    }
    @Transactional
    public void OrderSumit(Map<String, Object> map) {

        String key=map.get("defKey").toString();
        String val=map.get("status").toString();
        Map<String, Object> variables=new HashMap<String, Object>();
        variables.put(key,val);


        String taskId=(String) map.get("taskId");
        taskservice.complete(taskId,variables);

        String id=UUIDUtil.getTimeBasedUUID().toString();
        Date Createtime=new Date();
        map.put("id",id);
        map.put("Createtime",Createtime);
        createOrderMapper.OrderSumit(map);
    }

  @Transactional
    public void Stock_In(Map<String, Object> map) {
        String id=UUIDUtil.getTimeBasedUUID().toString();
        Date Createtime=new Date();
        map.put("id",id);
        map.put("Createtime",Createtime);
        createOrderMapper.Stock_In(map);
    }

    @Override
    public List<Map<String, Object>> findStock_In() {
        return createOrderMapper.findStock_In();
    }

    @Override
    public void updateDrug(Map map) {
        createOrderMapper.updateDrug(map);
    }

}