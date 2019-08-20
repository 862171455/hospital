package org.java.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import java.util.UUID;


public class MD5Demo {
    
    
    
    
    
    public static String tomd5(String password){
		SimpleHash hash= new SimpleHash("md5",password,"d102",3);
        return hash.toString();
    }
    


























    private String pwd ="123456";//原始密码
    
    
    @Test
    public void first(){

        Md5Hash md5 = new Md5Hash(pwd,"d102",3);
        System.out.println("加密后的结果是:"+md5.toString());

    }
    @Test
    public void second(){
        SimpleHash hash = new SimpleHash("md5",pwd,"d102",3);
        System.out.println("加密后的结果是:"+hash.toString());
    }
    @Test
    public void length(){
        String md5="074fdaff74a3fd7bcc109532ab5a9bed";
        int length = md5.length();
        System.out.println(length);
    }
    @Test
    public  void uuid() {
        for(int i=0;i<10;i++){
            String uuid = UUID.randomUUID().toString();//.replaceAll("-", "");
            System.out.println(uuid);
        }
    }
    
}
