package com.xzx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xzx.constant.ConstantParam;
import com.xzx.dto.BoxCalculateDto;
import com.xzx.entity.Task;
import com.xzx.servie.DataScienceService;
import com.xzx.servie.ITaskService;
import com.xzx.util.DataScienceUtil;
import com.xzx.vo.BoxCalculateVo;
import com.xzx.vo.BoxResVo;
import com.xzx.vo.RecommendParamVo;
import com.xzx.vo.RecommendVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Api(value = "DataScienceController", tags = "数据分析模块")
@RestController
@Slf4j
public class DataScienceController {

    @Autowired
    private DataScienceService dataScienceService;

    @Autowired
    private ITaskService taskService;

    @ApiOperation("进行票房预测，返回结果id号")
    @PostMapping("/ds/boxValue")
    public String getBoxValue(BoxCalculateVo boxCalculateVo) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        BoxCalculateDto boxCalculateDto = new BoxCalculateDto();
        boxCalculateDto.setBoxCalculateVo(boxCalculateVo);
        boxCalculateDto.setId(id);
        dataScienceService.boxCalculate(boxCalculateDto);
        return id;
    }

    @ApiOperation("返回票房预测结果")
    @GetMapping("/ds/boxValueRes")
    public BoxResVo getBoxValueRes(String id) {
        Task task = taskService.getById(id);
        JSONObject response = JSON.parseObject(task.getResponse());
        if (response == null) response = new JSONObject();
        long duration = Math.abs(Duration.between(task.getStartTime(), LocalDateTime.now()).toMinutes());
        if ((task.getStatus() == ConstantParam.INIT_STATUS || task.getStatus() == ConstantParam.RUNNING_STATUS) &&  duration > 5) {
            task.setStatus(ConstantParam.ERR_STATUS);
            taskService.updateById(task);
        }
        response.put("status", task.getStatus());
        response.put("msg", DataScienceUtil.getMsgByStatus(task.getStatus()));
        return response.toJavaObject(BoxResVo.class);
    }

    @ApiOperation("获得角色推荐结果")
    @PostMapping("/ds/chaRecommend")
    public List<RecommendVo> getChaRecommend(RecommendParamVo recommendParamVo) {
        return dataScienceService.characterRecommend(recommendParamVo);
    }

}
