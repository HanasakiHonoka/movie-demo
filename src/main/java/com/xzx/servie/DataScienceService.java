package com.xzx.servie;

import com.xzx.dto.BoxCalculateDto;
import com.xzx.vo.BoxCalculateVo;
import com.xzx.vo.BoxResVo;
import com.xzx.vo.RecommendParamVo;
import com.xzx.vo.RecommendVo;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface DataScienceService {

    @Async
    public void boxCalculate(BoxCalculateDto boxCalculateDto);

    public List<RecommendVo> characterRecommend(RecommendParamVo recommendParamVo);
}
