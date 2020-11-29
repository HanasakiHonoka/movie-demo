package com.xzx.controller;

import com.xzx.dto.CompanyQueryDTO;
import com.xzx.servie.ICompanyService;
import com.xzx.vo.CompanyVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname CompanyController
 * @Description
 * @Date 2020/11/29 11:17
 * @Author XZX
 * @Version 1.0
 */
@Slf4j
@Api(value = "CompanyController", tags = "公司模块")
@RestController
public class CompanyController {

    @Autowired
    ICompanyService companyService;

    @GetMapping("/company/list")
    public List<CompanyVO> list(CompanyQueryDTO companyQueryDTO) {
        return companyService.getVOList(companyQueryDTO);
    }
}
