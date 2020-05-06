package com.xzx.servie.impl;

import com.alibaba.fastjson.JSON;
import com.xzx.servie.DataScienceService;
import com.xzx.util.SocketUtil;
import com.xzx.vo.BoxCalculateVo;
import com.xzx.vo.BoxResVo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DataScienceServiceImpl implements DataScienceService {

    @Override
    public BoxResVo boxCalculate(BoxCalculateVo boxCalculateVo) {
        StringBuilder sb = SocketUtil.getSocket(JSON.toJSONString(boxCalculateVo));
        if (sb != null) {
            Map res = JSON.parseObject(sb.toString(), Map.class);
            System.out.println("res = " + res);
        }
        return null;
    }
}
