package com.xzx.vo;

import com.xzx.dto.DirectorWithMovie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MgtDirectorListVo {

    private List<DirectorWithMovie> directors;

}
