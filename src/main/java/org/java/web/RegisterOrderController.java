package org.java.web;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.TaskQuery;
import org.java.service.registerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
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
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String user = (String) user1.get("patient_username");
        List<Map<String, Object>> list = registerOrderService.showTask(user);
        model.addAttribute("list", list);
        return "/order/registerOrder";

    }

    @RequestMapping("/findRegisterOrder")
    public String showTask1(HttpSession ses, Model model) {
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
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
    public String showProcessInstance_reg(Model model) {
        // 得到正在运行中的流程中实例（流程实例的信息+它对应的业务数据）
        List<Map<String, Object>> list = registerOrderService.showProcessInstance();
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
    public String showclaimTask(HttpSession ses, Model model) {
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String userId = (String) user1.get("NAME");
        List<Map<String, Object>> list = registerOrderService.showclaimTask(userId);
        model.addAttribute("list", list);
        return "/order/showclaimTask_reg";
    }

    @RequestMapping("/claim_reg/{taskId}")
    public String claim(@PathVariable("taskId") String taskId, HttpSession ses) {
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String userId = (String) user1.get("NAME");
        registerOrderService.claimTask(userId, taskId);
        return "redirect:/showclaimTask_reg";
    }

    @RequestMapping("/showback_reg")
    public String showback(HttpSession ses, Model model) {
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String userId = (String) user1.get("NAME");
        List<Map<String, Object>> list = registerOrderService.showback(userId);
        model.addAttribute("list", list);
        return "/order/showback_reg";
    }

    @RequestMapping("/back_reg/{taskId}")
    public String back(@PathVariable("taskId") String taskId) {
        String userId = null;
        TaskQuery query = taskService.createTaskQuery();
        query.taskId(taskId);
        taskService.claim(taskId, userId);

        return "redirect:/showback_reg";
    }

    @RequestMapping("/audit_reg/{taskId}/{regId}/{DefKey}")
    public String audit(@PathVariable("taskId") String taskId, @PathVariable("regId") String regId, @PathVariable("DefKey") String DefKey, Model model, @RequestParam Map map1) {
        Map<String, Object> map = registerOrderService.findOrder(regId);
        map.put("createTime", new Date());//采购单创单时间
        model.addAttribute("map", map);
        model.addAttribute("taskId", taskId);
        model.addAttribute("regId", regId);
        model.addAttribute("DefKey", DefKey);
        return "/order/showAudit_reg";

    }

    @RequestMapping("/OrderSumit_reg")
    public String OrderSumit(@RequestParam Map<String, Object> map, HttpSession ses) {
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String user = (String) user1.get("NAME");
        map.put("user", user);
        String eat = (String) map.get("eat");
        map.put("eat", eat);
        registerOrderService.OrderSumit(map);
        return "redirect:/findRegisterOrder";

    }

    @RequestMapping("/out_reg/{taskId}/{regId}/{DefKey}")
    public String out_reg(@PathVariable("taskId") String taskId, @PathVariable("regId") String regId, @PathVariable("DefKey") String DefKey, Model model) {
        System.out.println("asa a  aasA");
        Map<String, Object> map = registerOrderService.dispose(regId);
        Map<String, Object> map1 = registerOrderService.doctorMed(regId);
        Map<String, Object> map2 = registerOrderService.check_body(regId);
        Map<String, Object> map3 = registerOrderService.checkover(regId);
        Map<String, Object> map4 = registerOrderService.Cure(regId);
        Map<String, Object> map5 = registerOrderService.Finance(regId);
        Map<String, Object> map6 = registerOrderService.Operation(regId);
        Map<String, Object> map7 = registerOrderService.Operationover(regId);

        if (map != null && map1 == null && map2 ==null &&map3 ==null &&map4 ==null &&map5 ==null &&map6 ==null &&map7 ==null) {
            map.put("createTime", new Date());
            model.addAttribute("map", map);
            model.addAttribute("taskId", taskId);
            model.addAttribute("regId", regId);
            model.addAttribute("DefKey", DefKey);
            return "/order/out_reg";
        }
        if (map != null && map1 != null && map2 ==null && map3 ==null &&map4 ==null &&map5 ==null&&map6 ==null &&map7 ==null) {
            map.put("createTime", new Date());
            model.addAttribute("map", map);
            model.addAttribute("map1", map1);
            model.addAttribute("taskId", taskId);
            model.addAttribute("regId", regId);
            model.addAttribute("DefKey", DefKey);
            return "/order/out_reg1";
        }
        if (map != null && map1 != null && map2 !=null && map3 !=null &&map4 ==null &&map5 ==null&&map6 ==null &&map7 ==null) {
            map.put("createTime", new Date());
            model.addAttribute("map", map);
            model.addAttribute("map1", map1);
            model.addAttribute("map2", map2);
            model.addAttribute("map3", map3);
            model.addAttribute("taskId", taskId);
            model.addAttribute("regId", regId);
            model.addAttribute("DefKey", DefKey);
            return "/order/out_reg2";
        }
        if (map != null && map1 != null && map2 !=null && map3 !=null &&map4 !=null &&map5 !=null&&map6 ==null &&map7 ==null) {
            map.put("createTime", new Date());
            model.addAttribute("map", map);
            model.addAttribute("map1", map1);
            model.addAttribute("map2", map2);
            model.addAttribute("map3", map3);
            model.addAttribute("map4", map4);
            model.addAttribute("map5", map5);
            model.addAttribute("taskId", taskId);
            model.addAttribute("regId", regId);
            model.addAttribute("DefKey", DefKey);
            return "/order/out_reg3";
        }
        if (map != null && map1 != null && map2 !=null && map3 !=null &&map4 !=null &&map5 !=null&&map6 !=null &&map7 !=null) {
            map.put("createTime", new Date());
            model.addAttribute("map", map);
            model.addAttribute("map1", map1);
            model.addAttribute("map2", map2);
            model.addAttribute("map3", map3);
            model.addAttribute("map4", map4);
            model.addAttribute("map5", map5);
            model.addAttribute("map6", map6);
            model.addAttribute("map7", map7);
            model.addAttribute("taskId", taskId);
            model.addAttribute("regId", regId);
            model.addAttribute("DefKey", DefKey);
            return "/order/out_reg4";
        }
        return null;
    }

    @RequestMapping("/med_reg/{taskId}/{regId}/{DefKey}")
    public String medicine(@PathVariable("taskId") String taskId, @PathVariable("regId") String regId, @PathVariable("DefKey") String DefKey, Model model) {
        Map<String, Object> map = registerOrderService.findOrder(regId);
        map.put("createTime", new Date());//采购单创单时间
        model.addAttribute("map", map);
        model.addAttribute("taskId", taskId);
        model.addAttribute("regId", regId);
        model.addAttribute("DefKey", DefKey);

        return "/order/med_reg";

    }

    @RequestMapping("/doctor_med_reg")
    public String doctor_med(@RequestParam Map<String, Object> map, HttpSession ses) {
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String user = (String) user1.get("NAME");
        map.put("user", user);
        registerOrderService.registerOrder(map);
        return "redirect:/findRegisterOrder";

    }

    @RequestMapping("/finance_reg/{taskId}/{regId}")
    public String finance_reg(@PathVariable("taskId") String taskId, @PathVariable("regId") String regId, Model model) {
        Map<String, Object> map = registerOrderService.findOrder(regId);
        Map<String,Object> map1=registerOrderService.doctorMed(regId);
        map.put("createTime", new Date());
        model.addAttribute("map",map);
        model.addAttribute("map1",map1);
        model.addAttribute("taskId", taskId);
        model.addAttribute("regId", regId);
        return "/order/finance_reg";

    }

    @RequestMapping("/check_reg/{taskId}/{regId}/{DefKey}")
    public String check_reg(@PathVariable("taskId") String taskId, @PathVariable("regId") String regId, @PathVariable("DefKey") String DefKey, Model model, @RequestParam Map map1) {
        Map<String, Object> map = registerOrderService.findOrder(regId);
        map.put("createTime", new Date());//采购单创单时间
        model.addAttribute("map", map);
        model.addAttribute("taskId", taskId);
        model.addAttribute("regId", regId);
        model.addAttribute("DefKey", DefKey);

        return "/order/check_reg";

    }

    @RequestMapping("/detailsCheck_reg")
    public String detailsCheck_reg(@RequestParam Map<String, Object> map, HttpSession ses) {
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String user = (String) user1.get("NAME");
        String val = (String) map.get("detailsCheck1");
        String[] split = val.split(",");
        String vals = split[0];
        String detailsCheck = split[1];
        map.put("vals", vals);
        map.put("detailsCheck", detailsCheck);
        map.put("user", user);
        registerOrderService.detailsCheck(map);
        ses.setAttribute("detailsCheck",detailsCheck);
        return "redirect:/payjc";

    }

    @RequestMapping("/check_A/{taskId}")
    public String check_A(@PathVariable("taskId") String taskId) {
        registerOrderService.storage(taskId);
        return "redirect:/findRegisterOrder";

    }

    @RequestMapping("/checkover/{taskId}/{regId}/{DefKey}")
    public String checkover(@PathVariable("taskId") String taskId, @PathVariable("regId") String regId, @PathVariable("DefKey") String DefKey, Model model) {
        Map<String, Object> map = registerOrderService.findOrder(regId);
        map.put("createTime", new Date());//采购单创单时间
        model.addAttribute("map", map);
        model.addAttribute("taskId", taskId);
        model.addAttribute("regId", regId);
        model.addAttribute("DefKey", DefKey);

        return "/order/checkover";

    }

    @RequestMapping("/checkover_reg")
    public String checkover_reg(@RequestParam Map<String, Object> map, HttpSession ses) {
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String user = (String) user1.get("NAME");
        map.put("user", user);
        registerOrderService.checkOver(map);
        return "redirect:/findRegisterOrder";

    }

    @RequestMapping("/financeC/{taskId}/{regId}")
    public String financeC(@PathVariable("taskId") String taskId,@PathVariable("regId") String regId, Model model) {
        Map<String, Object> map = registerOrderService.findOrder(regId);
        map.put("createTime", new Date());//采购单创单时间
        model.addAttribute("map", map);
        model.addAttribute("taskId", taskId);
        model.addAttribute("regId", regId);
        return "/order/financeC";
    }

    @RequestMapping("/bed/{taskId}/{regId}/{DefKey}")
    public String bed(@PathVariable("taskId") String taskId, @PathVariable("regId") String regId, @PathVariable("DefKey") String DefKey, Model model) {
        Map<String, Object> map = registerOrderService.findOrder(regId);
        map.put("createTime", new Date());//采购单创单时间
        model.addAttribute("map", map);
        model.addAttribute("taskId", taskId);
        model.addAttribute("regId", regId);
        model.addAttribute("DefKey", DefKey);

        return "/order/bed_reg";

    }

    @RequestMapping("/bed_reg")
    public String bed_reg(@RequestParam Map<String, Object> map, HttpSession ses) {
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String user = (String) user1.get("NAME");
        map.put("user", user);
        registerOrderService.cure(map);
        return "redirect:/findRegisterOrder";

    }

    @RequestMapping("/operation/{taskId}/{regId}/{DefKey}")
    public String operation(@PathVariable("taskId") String taskId, @PathVariable("regId") String regId, @PathVariable("DefKey") String DefKey, Model model) {
        Map<String, Object> map = registerOrderService.findOrder(regId);
        map.put("createTime", new Date());//采购单创单时间
        model.addAttribute("map", map);
        model.addAttribute("taskId", taskId);
        model.addAttribute("regId", regId);
        model.addAttribute("DefKey", DefKey);

        return "/order/operation";

    }

    @RequestMapping("/operation_reg")
    public String operation_reg(@RequestParam Map<String, Object> map, HttpSession ses) {
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String user = (String) user1.get("NAME");
        String val = (String) map.get("operationtype");
        System.out.println(val);
        String[] split = val.split(",");
        String vals = split[0];
        String operationtype = split[1];
        map.put("vals", vals);
        map.put("operationtype", operationtype);
        map.put("user", user);
        registerOrderService.operation(map);
        ses.setAttribute("operationtype",operationtype);
        return "redirect:/payss";

    }

    @RequestMapping("/operationA/{taskId}")
    public String operationA(@PathVariable("taskId") String taskId) {
        registerOrderService.storage(taskId);
        return "redirect:/findRegisterOrder";

    }

    @RequestMapping("/operationOver/{taskId}/{regId}/{DefKey}")
    public String operationOver(@PathVariable("taskId") String taskId, @PathVariable("regId") String regId, @PathVariable("DefKey") String DefKey, Model model) {
        Map<String, Object> map = registerOrderService.findOrder(regId);
        map.put("createTime", new Date());//采购单创单时间
        model.addAttribute("map", map);
        model.addAttribute("taskId", taskId);
        model.addAttribute("regId", regId);
        model.addAttribute("DefKey", DefKey);

        return "/order/operationOver";

    }

    @RequestMapping("/operationOver_reg")
    public String operationOver_reg(@RequestParam Map<String, Object> map, HttpSession ses) {
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String user = (String) user1.get("NAME");
        map.put("user", user);
        registerOrderService.operationOver(map);
        return "redirect:/findRegisterOrder";

    }

    @RequestMapping("/usertask17/{taskId}")
    public String usertask17(@PathVariable("taskId") String taskId) {
        registerOrderService.storage(taskId);
        return "redirect:/findRegisterOrder";

    }

    @RequestMapping("/out_hospital/{taskId}")
    public String out_hospital(@PathVariable("taskId") String taskId) {
        registerOrderService.storage(taskId);
        return "redirect:/user/findRegisterOrder";

    }
    @RequestMapping("/finance_money/{taskId}")
    public String  finance_money(@PathVariable("taskId") String taskId) {
        registerOrderService.storage(taskId);
        return "redirect:/user/findRegisterOrder";

    }
    @RequestMapping("/financeC_money")
    public String financeC_money(@RequestParam Map<String, Object> map, HttpSession ses) {
        Map<String, Object> user1 = (Map<String, Object>) ses.getAttribute("user");
        String user = (String) user1.get("patient_username");
        map.put("user", user);
        registerOrderService.finance(map);
        String zy = (String) map.get("money_med");
        ses.setAttribute("zy", zy);
        return "redirect:/payzy";

    }
}

