package com.xzx.servie.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.dto.CompanyQueryDTO;
import com.xzx.entity.Company;
import com.xzx.mapper.CompanyMapper;
import com.xzx.servie.ICompanyService;
import com.xzx.vo.CompanyVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname CompanyServiceImpl
 * @Description
 * @Date 2020/11/29 11:57
 * @Author XZX
 * @Version 1.0
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {
    @Override
    public List<CompanyVO> getVOList(CompanyQueryDTO companyQueryDTO) {
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(companyQueryDTO.getCompanyName()), Company.COMPANY_NAME, companyQueryDTO.getCompanyName());
        queryWrapper.eq(companyQueryDTO.getCompanyId() != null, Company.COMPANY_ID, companyQueryDTO.getCompanyId());
        queryWrapper.last("limit 10");
        List<Company> companyList = baseMapper.selectList(queryWrapper);
        List<CompanyVO> voList = new ArrayList<>();
        for (Company company : companyList) {
            CompanyVO companyVO = BeanUtil.copyProperties(company, CompanyVO.class);
            companyVO.setId(company.getCompanyId());
            companyVO.setValue(company.getCompanyName());
            voList.add(companyVO);
        }
        return voList;
    }
}
