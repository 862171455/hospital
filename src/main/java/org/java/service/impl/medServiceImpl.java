package org.java.service.impl;

import org.java.dao.medicineMapper;
import org.java.service.medService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/11 16:15
 */
@Service
public class medServiceImpl implements medService {
    @Autowired
    private medicineMapper medicineMapper;
    
    
    @Override
    public List<Map<String, Object>> findallDrug() {
        return medicineMapper.findallDrug();
    }
    
    @Override
    public int drugCount(String dru_name,Integer dru_drugstore_no) {
        return medicineMapper.drugCount(dru_name,dru_drugstore_no);
    }

    @Override
    public List<Map<String,Object>> findDrug(int page,int rows,String dru_name,int dru_drugstore_no) {
        int start =(page-1)*rows;
        return medicineMapper.findDrug(start,rows,dru_name,dru_drugstore_no);

    }

    @Override
    @Transactional
    public void add(Map map) {
        medicineMapper.add(map);
    }

    @Override
    @Transactional
    public void update(Map map) {
        medicineMapper.update(map);
    }

    @Override
    @Transactional
    public void del(Integer dru_no) {
        medicineMapper.del(dru_no);
    }

    @Override
    public List<Map<String, Object>> findSupplier() {
        return medicineMapper.findSupplier();
    }

    @Override
    public List<Map<String, Object>> findChinese_medicine() {
        return medicineMapper.findChinese_medicine();
    }

    @Override
    public List<Map<String, Object>> findWestern_medicine() {
        return medicineMapper.findWestern_medicine();
    }

    @Override
    public List<Map<String, Object>> findDrugstore() {
        return medicineMapper.findDrugstore();
    }

    @Override
    public int purCount(Map map) {
        return medicineMapper.purCount(map);
    }

    @Override
    public Map findPurId(String username) {
        return medicineMapper.findPurId(username);
    }

    @Override
    public int managerCount(Map map) {
        return medicineMapper.managerCount(map);
    }

    @Override
    public Map findManagerId(String username) {
        return medicineMapper.findManagerId(username);
    }

    @Override
    public void updatePur(String name,String password1) {
        medicineMapper.updatePur(name,password1);
    }
}
