package com.xzx.convert;

import cn.hutool.core.date.DateUtil;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

public class ConvertStringToDate extends AbstractBeanField {

    @Override
    protected Object convert(String s){
        if(s == null || s.equals("")) {
            return null;
        }
        Date date = DateUtil.parse(s, "dd/MM/yyyy");
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
