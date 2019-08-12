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
}
