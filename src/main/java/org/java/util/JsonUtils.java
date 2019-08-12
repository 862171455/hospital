package org.java.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {  
  

    private static final ObjectMapper MAPPER = new ObjectMapper();  
  
    /** 
     *
     * <p>Title: pojoToJson</p> 
     * <p>Description: </p> 
     * @param data 
     * @return 
     */  
    public static String objectToJson(Object data) {  
        try {  
            String string = MAPPER.writeValueAsString(data);  
            return string;  
        } catch (JsonProcessingException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
      

    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {  
        try {  
            T t = MAPPER.readValue(jsonData, beanType);  
            return t;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
      

    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {  
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);  
        try {  
            List<T> list = MAPPER.readValue(jsonData, javaType);  
            return list;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        return null;  
    }
    
    
    public static List<Map<String, Object>> jsonToListMap(String json){
        List<Object> list = JSON.parseArray(json);
        List< Map<String,Object>> listw = new ArrayList<Map<String,Object>>();
        for (Object object : list){
            Map <String,Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
            listw.add(ret);
        }
        return listw;
       
       
        
        
    }
    public static Map<String, Object> jsonToMap(String json) {
        JSON jsonObject = JSONObject.parseObject(json);
        Map<String, Object> map = (Map)jsonObject;
        return map;
    }
    
    
}  