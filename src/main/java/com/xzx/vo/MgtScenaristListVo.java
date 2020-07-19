package com.xzx.vo;

import com.xzx.dto.ScenaristWithMovie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MgtScenaristListVo {

    private List<ScenaristWithMovie> scenarists;

}
