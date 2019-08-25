package org.java.web;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.TaskQuery;
import org.java.service.CreateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/18 11:56
 */
@Controller
public class CreateOrderController {
    @Autowired
    private CreateOrderService createOrderService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private HistoryService historyService;


    @RequestMapping("/createOrder")
    public String createOrder(@RequestParam Map map, HttpSession session){

            // 通过service层，做两件事情: 1、启动一个流程实例， 2、向采购单表中，增加一条采购记录(业务数据)
            // 从session中，取得user,存放 到map向后传递
            String userId = (String) session.getAttribute("user");
            map.put("userId",userId);

            // 创建采购单
            createOrderService.createOrder(map);

            return "redirect:/showTask";
    }
    @RequestMapping("/showTask")
    public String showTask(HttpSession ses, Model model) {
        String user = (String) ses.getAttribute("user");
        List<Map<String, Object>> list = createOrderService.showTask(user);
        model.addAttribute("list", list);
        return "/order/showTask";

    }
    @RequestMapping("/showProcessInstance")
    public String showProcessInstance(Model model) {
        // 得到正在运行中的流程中实例（流程实例的信息+它对应的业务数据）
        List<Map<String,Object>> list =createOrderService.showProcessInstance();
        model.addAttribute("list", list);
        return "/order/showProcessInstance";
    }
    @RequestMapping("/deleteTask/{processInstanceId}")
    public String deleteProcessInstance(
            @PathVariable("processInstanceId") String processInstanceId) {

        runtimeService.deleteProcessInstance(processInstanceId, "太像蔡徐坤");


        return "redirect:/showProcessInstance";
    }
        @RequestMapping("/showActiveMap/{processInstanceId}")
        public String showActiveMap(
                @PathVariable("processInstanceId") String processInstanceId,
                Model model) {
            ProcessInstanceQuery query = runtimeService
                    .createProcessInstanceQuery();
            ProcessInstance Instance = query.processInstanceId(processInstanceId)
                    .singleResult();
            String ProcessDefinitionId = Instance.getProcessDefinitionId();
            ProcessDefinitionEntity entity = (ProcessDefinitionEntity) repositoryService
                    .getProcessDefinition(ProcessDefinitionId);
            String act = Instance.getActivityId();
            ActivityImpl Act = entity.findActivity(act);
            int x = Act.getX();
            int y = Act.getY();
            int Width = Act.getWidth();
            int Height = Act.getHeight();
            model.addAttribute("x", x);
            model.addAttribute("y", y);
            model.addAttribute("w", Width);
            model.addAttribute("h", Height);
            String deploymentId = entity.getDeploymentId();
            String png = entity.getDiagramResourceName();
            model.addAttribute("deploymentId", deploymentId);
            model.addAttribute("png", png);
            // 跳转到页面，显示流程图
            return "/order/showActiveMap";
        }
    @RequestMapping("showResourceName/{deploymentId}/{name}")
    public void showResourceName(@PathVariable("deploymentId") String deploymentId, @PathVariable("name") String name, HttpServletResponse res) throws Exception{
        InputStream in = repositoryService.getResourceAsStream(deploymentId, name);
        OutputStream out= res.getOutputStream();
        byte[] b=new byte[8192];
        int len=0;
        while((len=in.read(b,0,8192))!=-1){
            out.write(b,0,len);
        }
        out.close();
        in.close();
    }
    @RequestMapping("showHistoryTask/{processInstanceId}")
    public String showHistoryTask(
            @PathVariable("processInstanceId") String processInstanceId,
            Model model) {
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();
        List<HistoricTaskInstance> list = query.processInstanceId(processInstanceId).list();
        model.addAttribute("list", list);
        return "/order/historyTask";



    }
    @RequestMapping("submitOrder/{taskId}")
    public String submitOrder(@PathVariable("taskId") String taskId) {

        //taskService.complete(taskId);
        createOrderService.submitOrder(taskId);

        return "redirect:/showTask";
    }
    @RequestMapping("/showclaimTask")
    public String showclaimTask(HttpSession ses,Model model) {
        String userId = (String) ses.getAttribute("user");
        List<Map<String, Object>> list = createOrderService.showclaimTask(userId);
        model.addAttribute("list",list);
        return "/order/showclaimTask";
    }
    @RequestMapping("/claim/{taskId}")
    public String claim(@PathVariable("taskId") String taskId,HttpSession ses) {
        String userId = (String) ses.getAttribute("user");
        createOrderService.claimTask(userId, taskId);
        return "redirect:/showclaimTask";
    }
    @RequestMapping("/showback")
    public String showback(HttpSession ses,Model model) {
        String userId = (String) ses.getAttribute("user");
        List<Map<String, Object>> list = createOrderService.showback(userId);
        model.addAttribute("list",list);
        return "/order/showback";
    }
    @RequestMapping("/back/{taskId}")
    public String back(@PathVariable("taskId") String taskId) {
        String userId=null;
        TaskQuery query = taskService.createTaskQuery();
        query.taskId(taskId);
        taskService.claim(taskId,userId);

        return "redirect:/showback";
    }
    @RequestMapping("/audit/{taskId}/{orderId}/{DefKey}")
    public String audit(@PathVariable("taskId") String taskId,@PathVariable("orderId") String orderId,@PathVariable("DefKey") String DefKey, Model model) {
        Map<String, Object> map = createOrderService.findOrder(orderId);
        model.addAttribute("map", map);
        model.addAttribute("taskId", taskId);
        model.addAttribute("orderId", orderId);
        model.addAttribute("DefKey", DefKey);
        return "/order/showAudit";

    }
    @RequestMapping("/OrderSumit")
    public String OrderSumit(@RequestParam Map<String,Object> map,HttpSession ses){
        String user=(String) ses.getAttribute("user");
        map.put("user", user);
        createOrderService.OrderSumit(map);
        return "redirect:/showTask";

    }
    @RequestMapping("/storage/{taskId}/{name}/{price}/{content}/{assignee}/{count}")
    public String storage(@PathVariable("taskId") String taskId,@PathVariable("name") String name,
                          @PathVariable("price") String price,@PathVariable("content") String content,
                          @PathVariable("assignee") String assignee,@PathVariable("count") String count
                          ){
        taskService.complete(taskId);
        Map<String,Object> map =new HashMap<>();
        map.put("name",name);
        map.put("price",price);
        map.put("content",content);
        map.put("assignee",assignee);
        map.put("count",count);
        createOrderService.updateDrug(map);
        createOrderService.Stock_In(map);
        return "redirect:/showTask";

    }

    @RequestMapping("/Stock_In")
    public String Stock_In(Model model){
        List<Map<String, Object>> list = createOrderService.findStock_In();
        model.addAttribute("list",list);
        return "/order/Stock_In";

    }
    }
