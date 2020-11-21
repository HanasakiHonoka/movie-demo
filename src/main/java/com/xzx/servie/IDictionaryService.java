package com.xzx.servie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzx.dto.DictionaryQueryDTO;
import com.xzx.entity.Dictionary;

import java.util.List;

/**
 * @Classname IDictionaryService
 * @Description
 * @Date 2020/11/19 20:20
 * @Author XZX
 * @Version 1.0
 */
public interface IDictionaryService extends IService<Dictionary> {

    List<Dictionary> getDictionaryList(DictionaryQueryDTO dictionaryQueryDTO);

    Dictionary getByDicNo(String dicNo);
}
