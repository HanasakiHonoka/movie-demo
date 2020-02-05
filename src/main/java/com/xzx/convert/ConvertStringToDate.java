package com.xzx.convert;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertStringToDate extends AbstractBeanField {

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if(s.equals("") || s == null) {
            return null;
        }
        Date date = null;
        try {
            //System.out.println(sdf.parse(s));
            //System.out.println(s);
            date = new SimpleDateFormat("dd/MM/yyyy").parse(s);
            //System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return date;
        }
    }
}
