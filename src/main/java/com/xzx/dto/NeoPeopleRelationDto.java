package com.xzx.dto;

import com.xzx.entity.NeoMovie;
import com.xzx.entity.NeoPeople;
import com.xzx.entity.NeoRelation;

public class NeoPeopleRelationDto {

    private NeoPeople neoPeople;
    private String neoRelation;
    private NeoMovie neoMovie;

    public NeoPeople getNeoPeople() {
        return neoPeople;
    }

    public void setNeoPeople(NeoPeople neoPeople) {
        this.neoPeople = neoPeople;
    }


    public String getNeoRelation() {
        return neoRelation;
    }

    public void setNeoRelation(String neoRelation) {
        this.neoRelation = neoRelation;
    }

    public NeoMovie getNeoMovie() {
        return neoMovie;
    }

    public void setNeoMovie(NeoMovie neoMovie) {
        this.neoMovie = neoMovie;
    }
}
