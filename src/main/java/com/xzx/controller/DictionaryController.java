package com.xzx.controller;

import com.xzx.dto.DictionaryQueryDTO;
import com.xzx.entity.Dictionary;
import com.xzx.servie.IDictionaryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname DictionaryController
 * @Description
 * @Date 2020/11/19 20:21
 * @Author XZX
 * @Version 1.0
 */
@Slf4j
@Api(value = "DictionaryController", tags = "字典表")
@RestController("/dictionary")
public class DictionaryController {
    @Autowired
    IDictionaryService dictionaryService;

    @GetMapping("/list")
    public List<Dictionary> getDictionaryList(DictionaryQueryDTO dictionaryQueryDTO) {
        return dictionaryService.getDictionaryList(dictionaryQueryDTO);
    }
}
