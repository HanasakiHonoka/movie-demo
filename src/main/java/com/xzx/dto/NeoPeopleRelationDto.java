package com.xzx.dto;

import com.xzx.entity.NeoMovie;
import com.xzx.entity.NeoPeople;

public class NeoPeopleRelationDto {

    private NeoPeople start;
    private String neoRelation;
    private NeoMovie end;

    @Override
    public boolean equals(Object obj) {
        NeoPeopleRelationDto nprd = (NeoPeopleRelationDto) obj;
        return start.getName().equals(nprd.getStart().getName()) && end.getName().equals(nprd.getEnd().getName()) && neoRelation.equals(nprd.neoRelation);
        //return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return start.getName().hashCode() + end.getName().hashCode() + neoRelation.hashCode();
        //return super.hashCode();
    }

    public NeoPeople getStart() {
        return start;
    }

    public void setStart(NeoPeople start) {
        this.start = start;
    }


    public String getNeoRelation() {
        return neoRelation;
    }

    public void setNeoRelation(String neoRelation) {
        this.neoRelation = neoRelation;
    }

    public NeoMovie getEnd() {
        return end;
    }

    public void setEnd(NeoMovie end) {
        this.end = end;
    }
}
