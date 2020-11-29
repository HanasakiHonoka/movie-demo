package com.xzx.servie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzx.dto.CompanyQueryDTO;
import com.xzx.entity.Company;
import com.xzx.vo.CompanyVO;

import java.util.List;

/**
 * @Classname ICompanyService
 * @Description
 * @Date 2020/11/29 11:56
 * @Author XZX
 * @Version 1.0
 */
public interface ICompanyService extends IService<Company> {

    List<CompanyVO> getVOList(CompanyQueryDTO companyQueryDTO);
}
