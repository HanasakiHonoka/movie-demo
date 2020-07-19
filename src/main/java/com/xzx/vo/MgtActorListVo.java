package com.xzx.vo;

import com.xzx.dto.ActorWithMovie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MgtActorListVo {

    private List<ActorWithMovie> actors;

}
