package com.xzx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.xzx.convert.ConvertStringToDate;
import com.xzx.dto.ScenaristWithMovie;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
@Data
public class MgtScenaristPageVO {
    private Integer id;
    private String name;
    private String gender;
    private String occupation;
    private String constellation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;
    private String location;
    private Integer amount;
}
