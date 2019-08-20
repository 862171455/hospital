package org.java.web;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.java.service.CreateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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
        List<Map<String, Object>> list1= createOrderService.findOrder_supplier();
        Map<String, Object> map = list1.get(0);
        model.addAttribute("list", list);
        model.addAttribute("map",map);
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
}
