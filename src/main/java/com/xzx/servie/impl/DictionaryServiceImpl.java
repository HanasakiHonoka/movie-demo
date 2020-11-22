package com.xzx.servie.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.dto.DictionaryQueryDTO;
import com.xzx.entity.Dictionary;
import com.xzx.mapper.DictionaryMapper;
import com.xzx.servie.IDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname DictionaryServiceImpl
 * @Description
 * @Date 2020/11/19 20:20
 * @Author XZX
 * @Version 1.0
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

    @Override
    public List<Dictionary> getDictionaryList(DictionaryQueryDTO dto) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(dto.getDicType()), Dictionary.DIC_TYPE,dto.getDicType());
        queryWrapper.eq(StringUtils.isNotBlank(dto.getDicName()), Dictionary.DIC_NAME,dto.getDicName());
        queryWrapper.eq(StringUtils.isNotBlank(dto.getDicNo()), Dictionary.DIC_NO,dto.getDicNo());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Dictionary getByDicNo(String dicNo) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Dictionary.DIC_NO, dicNo);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public String getNameByDicNo(String dicNo) {
        Dictionary dic = this.getByDicNo(dicNo);
        if (dic != null) {
            return dic.getDicName();
        } else {
            return null;
        }
    }
}
