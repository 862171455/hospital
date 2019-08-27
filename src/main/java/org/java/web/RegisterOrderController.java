package org.java.web;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.TaskQuery;
import org.java.service.registerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/26 8:15
 */
@Controller
public class RegisterOrderController {
        @Autowired
        private registerOrderService registerOrderService;
        @Autowired
        private TaskService taskService;
        @Autowired
        private RuntimeService runtimeService;
        @Autowired
        private RepositoryService repositoryService;
        @Autowired
        private HistoryService historyService;
        @RequestMapping("/user/findRegisterOrder")
        public String showTask(HttpSession ses, Model model) {
             Map<String,Object> user1 = (Map<String, Object>) ses.getAttribute("user");
                String user = (String) user1.get("patient_username");
                List<Map<String, Object>> list = registerOrderService.showTask(user);
                model.addAttribute("list", list);
                return "/order/registerOrder";

        }
    @RequestMapping("/findRegisterOrder")
    public String showTask1(HttpSession ses, Model model) {
        Map<String,Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String user = (String) user1.get("NAME");
        List<Map<String, Object>> list = registerOrderService.showTask(user);
        model.addAttribute("list", list);
        return "/order/registerOrder";

    }
    @RequestMapping("/registerOrder/{taskId}")
    public String submitOrder(@PathVariable("taskId") String taskId) {

        //taskService.complete(taskId);
        registerOrderService.submitOrder(taskId);

        return "redirect:/user/findRegisterOrder";
    }
    @RequestMapping("/showProcessInstance_reg")
    public String showProcessInstance(Model model) {
        // 得到正在运行中的流程中实例（流程实例的信息+它对应的业务数据）
        List<Map<String,Object>> list =registerOrderService.showProcessInstance();
        model.addAttribute("list", list);
        return "/order/showProcessInstance_reg";
    }
    @Transactional
    @RequestMapping("/deleteTask_reg/{processInstanceId}")
    public String deleteProcessInstance(
            @PathVariable("processInstanceId") String processInstanceId) {

        runtimeService.deleteProcessInstance(processInstanceId, "太像蔡徐坤");


        return "redirect:/showProcessInstance_reg";
    }
    @RequestMapping("/showclaimTask_reg")
    public String showclaimTask(HttpSession ses,Model model) {
        Map<String,Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String userId = (String) user1.get("NAME");
        List<Map<String, Object>> list = registerOrderService.showclaimTask(userId);
        model.addAttribute("list",list);
        return "/order/showclaimTask_reg";
    }
    @RequestMapping("/claim_reg/{taskId}")
    public String claim(@PathVariable("taskId") String taskId,HttpSession ses) {
        Map<String,Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String userId = (String) user1.get("NAME");
       registerOrderService.claimTask(userId, taskId);
        return "redirect:/showclaimTask_reg";
    }
    @RequestMapping("/showback_reg")
    public String showback(HttpSession ses,Model model) {
        Map<String,Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String userId = (String) user1.get("NAME");
        List<Map<String, Object>> list = registerOrderService.showback(userId);
        model.addAttribute("list",list);
        return "/order/showback_reg";
    }
    @RequestMapping("/back_reg/{taskId}")
    public String back(@PathVariable("taskId") String taskId) {
        String userId=null;
        TaskQuery query = taskService.createTaskQuery();
        query.taskId(taskId);
        taskService.claim(taskId,userId);

        return "redirect:/showback_reg";
    }
    @RequestMapping("/audit_reg/{taskId}/{regId}/{DefKey}")
    public String audit(@PathVariable("taskId") String taskId,@PathVariable("regId") String regId,@PathVariable("DefKey") String DefKey, Model model,@RequestParam Map map1)
    {
       String eat = (String) map1.get("eat");
        Map<String, Object> map = registerOrderService.findOrder(regId);
        map.put("createTime", new Date());//采购单创单时间
        map.put("eat",eat);
        model.addAttribute("map", map);
        model.addAttribute("taskId", taskId);
        model.addAttribute("regId", regId);
        model.addAttribute("DefKey", DefKey);

        return "/order/showAudit_reg";

    }
    @RequestMapping("/OrderSumit_reg")
    public String OrderSumit(@RequestParam Map<String,Object> map, HttpSession ses){
        Map<String,Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String user = (String) user1.get("NAME");
        map.put("user", user);
        registerOrderService.OrderSumit(map);
        return "redirect:/findRegisterOrder";

    }
    @RequestMapping("/out_reg/{taskId}")
    public String storage(@PathVariable("taskId") String taskId){
        registerOrderService.storage(taskId);
        return "redirect:/user/findRegisterOrder";

    }

    @RequestMapping("/med_reg/{taskId}/{assignee}/{regId}/{DefKey}")
    public String medicine(@PathVariable("taskId") String taskId,@PathVariable("assignee") String assignee,@PathVariable("regId") String regId,@PathVariable("DefKey") String DefKey, Model model)
    {
        Map<String, Object> map = registerOrderService.findOrder(regId);
        map.put("createTime", new Date());//采购单创单时间
        model.addAttribute("map", map);
        model.addAttribute("taskId", taskId);
        model.addAttribute("regId", regId);
        model.addAttribute("DefKey", DefKey);

        return "/order/med_reg";

    }
    @RequestMapping("/doctor_med_reg")
    public String doctor_med(@RequestParam Map<String,Object> map, HttpSession ses){
        Map<String,Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String user = (String) user1.get("NAME");
        map.put("user", user);
        registerOrderService.registerOrder(map);
        return "redirect:/findRegisterOrder";

    }

}
