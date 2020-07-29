package com.xzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.xzx.servie.DataScienceService;
import com.xzx.vo.BoxCalculateVo;
import com.xzx.vo.BoxResVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "DataScienceController", tags = "数据分析模块")
@RestController
public class DataScienceController {

    @Autowired
    private DataScienceService dataScienceService;

    @ApiOperation("获得票房预测结果")
    @PostMapping("/ds/boxValue")
    public BoxResVo getBoxValue(BoxCalculateVo boxCalculateVo) {
        return dataScienceService.boxCalculate(boxCalculateVo);
    }

}
