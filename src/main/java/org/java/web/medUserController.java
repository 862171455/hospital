package org.java.web;


import org.java.service.medService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/15 19:16
 */
@Controller
public class medUserController {
    @Autowired
    private medService service;
    @GetMapping("/logout")
    public String logout(HttpSession ses) {
        ses.removeAttribute("user");
        return "/index";
    }
    @PostMapping("/login1")
    public String login1(HttpSession ses, @RequestParam Map map, Model model) {
        String model1 = (String) map.get("model");
        if (model1.equals("1")) {
            int count1 = service.purOrderCount(map);
            if (count1 == 0) {
                ses.setAttribute("err", "用户名或密码错误");
                return "/index";
            } else {
                ses.setAttribute("user", map.get("name"));
                Map map1 = service.findOrderId(map.get("name").toString());
                model.addAttribute("map1", map1);
            }

        }
        return "/show1";

    }


}
