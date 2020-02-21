package com.xzx.entity;

public class NeoRelation {

    private NeoPeople neoPeople;
    private NeoMovie neoMovie;
    private String relation;

    public NeoRelation() {
        super();
    }

    public NeoRelation(NeoPeople neoPeople, NeoMovie neoMovie, String relation) {
        this.neoPeople = neoPeople;
        this.neoMovie = neoMovie;
        this.relation = relation;
    }

    public NeoPeople getNeoPeople() {
        return neoPeople;
    }

    public void setNeoPeople(NeoPeople neoPeople) {
        this.neoPeople = neoPeople;
    }

    public NeoMovie getNeoMovie() {
        return neoMovie;
    }

    public void setNeoMovie(NeoMovie neoMovie) {
        this.neoMovie = neoMovie;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
