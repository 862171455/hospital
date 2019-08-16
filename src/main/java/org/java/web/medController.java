package org.java.web;

import org.java.service.medService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/11 16:19
 */
@RestController
public class medController {
    @Autowired
    private medService medService;
    @RequestMapping("drug")
    public Map drug(Integer page,Integer limit,String dru_name,@RequestParam(name = "dru_drugstore_no",defaultValue = "0") Integer dru_drugstore_no){
        Map map=new HashMap();
        List<Map<String,Object>> list = medService.findDrug(page,limit,dru_name,dru_drugstore_no);
       int count=medService.drugCount(dru_name,dru_drugstore_no);
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",list);
        return map;


    }
    @RequestMapping("/add")
    @ResponseBody
    public void add(@RequestParam Map map){
        medService.add(map);
    }
    @RequestMapping("/update")
    @ResponseBody
    public void update(@RequestParam Map map){

        medService.update(map);
    }
    @RequestMapping("/del")
    @ResponseBody
    public void del(Integer dru_no){

        medService.del(dru_no);
    }

    @RequestMapping("/sup")
    public Map sup(){
        Map map=new HashMap();
        List<Map<String,Object>> list = medService.findSupplier();
        map.put("code",0);
        map.put("msg","");
        map.put("data",list);
        return map;


    }
    @RequestMapping("/chinese_medicine")
    public Map chinese_medicine(){
        Map map=new HashMap();
        List<Map<String,Object>> list = medService.findChinese_medicine();
        map.put("code",0);
        map.put("msg","");
        map.put("data",list);
        return map;


    }
    @RequestMapping("/western_medicine")
    public Map western_medicine(){
        Map map=new HashMap();
        List<Map<String,Object>> list = medService.findWestern_medicine();
        map.put("code",0);
        map.put("msg","");
        map.put("data",list);
        return map;


    }
    @RequestMapping("/drugstore")
    public Map drugstore (){
        Map map=new HashMap();
        List<Map<String,Object>> list = medService.findDrugstore();
        for(Map<String,Object> m:list){
            int sto_count = (int) m.get("sto_count");
            int dru_drugnum=(int)m.get("dru_drugnum");
            int sto_surplus=sto_count-dru_drugnum;
            m.put("sto_surplus",sto_surplus);
        }
        map.put("code",0);
        map.put("msg","");
        map.put("data",list);
        return map;


    }

}
