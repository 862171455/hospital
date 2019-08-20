package org.java.web;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.java.service.CreateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        System.out.println(userId);
            map.put("userId",userId);

            // 创建采购单
            createOrderService.createOrder(map);

            return "redirect:/showTask";
    }
    @RequestMapping("/showTask")
    public String showTask(HttpSession ses, Model model) {
        System.out.println(666);
        String user = (String) ses.getAttribute("user");
        List<Map<String, Object>> list = createOrderService.showTask(user);
        model.addAttribute("list", list);
        return "/order/showTask";

    }
}
