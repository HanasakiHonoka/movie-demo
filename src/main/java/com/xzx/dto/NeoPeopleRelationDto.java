package com.xzx.dto;

import com.xzx.entity.NeoMovie;
import com.xzx.entity.NeoPeople;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NeoPeopleRelationDto {

    private NeoPeople start;
    private String neoRelation;
    private NeoMovie end;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NeoPeopleRelationDto)) return false;
        NeoPeopleRelationDto nprd = (NeoPeopleRelationDto) obj;
        return start.getId().equals(nprd.getStart().getId()) && end.getId().equals(nprd.getEnd().getId()) && neoRelation.equals(nprd.neoRelation);
        //return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return start.getId().hashCode() + end.getId().hashCode() + neoRelation.hashCode();
        //return super.hashCode();
    }
}
