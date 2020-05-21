package com.xzx.servie.impl;

import com.alibaba.fastjson.JSON;
import com.xzx.servie.DataScienceService;
import com.xzx.util.RestApiUtil;
import com.xzx.util.SocketUtil;
import com.xzx.vo.BoxCalculateVo;
import com.xzx.vo.BoxResVo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DataScienceServiceImpl implements DataScienceService {

    @Override
    public BoxResVo boxCalculate(BoxCalculateVo boxCalculateVo){
        System.out.println(boxCalculateVo.getActors());
        String res = RestApiUtil.sendToRestApi("http://127.0.0.1:5000/data/box", boxCalculateVo);
        BoxResVo boxResVo = new BoxResVo();
        if(res != null) {
            Map resMap = JSON.parseObject(res, Map.class);
            Object boxValue = resMap.get("boxValue");
            System.out.println(boxValue);
            int intBoxValue = 0;
            try {
                intBoxValue = Integer.parseInt((String) boxValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (intBoxValue < 0) {
                intBoxValue = -intBoxValue;
            }
            boxResVo.setBoxValue(intBoxValue);
        }else {
            boxResVo.setBoxValue(0);
        }
        return boxResVo;
    }
}
