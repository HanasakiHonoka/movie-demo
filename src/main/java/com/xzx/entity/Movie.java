package com.xzx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(value = "电影实体")
public class Movie {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.id
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.title
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.duration
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private Integer duration;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.release_time
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date releaseTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.release_area
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private String releaseArea;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.technology
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private String technology;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.type
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.second_type
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private String secondType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.boxoffice
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private Float boxoffice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.first_boxoffice
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private Float firstBoxoffice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.actor
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private String actor;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.issue_company
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private String issueCompany;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.manu_company
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private String manuCompany;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.is_ip
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private Boolean isIp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.is_sequel
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private Boolean isSequel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_table.is_network
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    private Boolean isNetwork;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.id
     *
     * @return the value of movie_table.id
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.id
     *
     * @param id the value for movie_table.id
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.title
     *
     * @return the value of movie_table.title
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.title
     *
     * @param title the value for movie_table.title
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.duration
     *
     * @return the value of movie_table.duration
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.duration
     *
     * @param duration the value for movie_table.duration
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.release_time
     *
     * @return the value of movie_table.release_time
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.release_time
     *
     * @param releaseTime the value for movie_table.release_time
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.release_area
     *
     * @return the value of movie_table.release_area
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public String getReleaseArea() {
        return releaseArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.release_area
     *
     * @param releaseArea the value for movie_table.release_area
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setReleaseArea(String releaseArea) {
        this.releaseArea = releaseArea == null ? null : releaseArea.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.technology
     *
     * @return the value of movie_table.technology
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public String getTechnology() {
        return technology;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.technology
     *
     * @param technology the value for movie_table.technology
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setTechnology(String technology) {
        this.technology = technology == null ? null : technology.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.type
     *
     * @return the value of movie_table.type
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.type
     *
     * @param type the value for movie_table.type
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.second_type
     *
     * @return the value of movie_table.second_type
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public String getSecondType() {
        return secondType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.second_type
     *
     * @param secondType the value for movie_table.second_type
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setSecondType(String secondType) {
        this.secondType = secondType == null ? null : secondType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.boxoffice
     *
     * @return the value of movie_table.boxoffice
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public Float getBoxoffice() {
        return boxoffice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.boxoffice
     *
     * @param boxoffice the value for movie_table.boxoffice
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setBoxoffice(Float boxoffice) {
        this.boxoffice = boxoffice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.first_boxoffice
     *
     * @return the value of movie_table.first_boxoffice
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public Float getFirstBoxoffice() {
        return firstBoxoffice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.first_boxoffice
     *
     * @param firstBoxoffice the value for movie_table.first_boxoffice
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setFirstBoxoffice(Float firstBoxoffice) {
        this.firstBoxoffice = firstBoxoffice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.actor
     *
     * @return the value of movie_table.actor
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public String getActor() {
        return actor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.actor
     *
     * @param actor the value for movie_table.actor
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setActor(String actor) {
        this.actor = actor == null ? null : actor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.issue_company
     *
     * @return the value of movie_table.issue_company
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public String getIssueCompany() {
        return issueCompany;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.issue_company
     *
     * @param issueCompany the value for movie_table.issue_company
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setIssueCompany(String issueCompany) {
        this.issueCompany = issueCompany == null ? null : issueCompany.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.manu_company
     *
     * @return the value of movie_table.manu_company
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public String getManuCompany() {
        return manuCompany;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.manu_company
     *
     * @param manuCompany the value for movie_table.manu_company
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setManuCompany(String manuCompany) {
        this.manuCompany = manuCompany == null ? null : manuCompany.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.is_ip
     *
     * @return the value of movie_table.is_ip
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public Boolean getIsIp() {
        return isIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.is_ip
     *
     * @param isIp the value for movie_table.is_ip
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setIsIp(Boolean isIp) {
        this.isIp = isIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.is_sequel
     *
     * @return the value of movie_table.is_sequel
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public Boolean getIsSequel() {
        return isSequel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.is_sequel
     *
     * @param isSequel the value for movie_table.is_sequel
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setIsSequel(Boolean isSequel) {
        this.isSequel = isSequel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_table.is_network
     *
     * @return the value of movie_table.is_network
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public Boolean getIsNetwork() {
        return isNetwork;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_table.is_network
     *
     * @param isNetwork the value for movie_table.is_network
     *
     * @mbg.generated Wed Dec 11 17:11:59 CST 2019
     */
    public void setIsNetwork(Boolean isNetwork) {
        this.isNetwork = isNetwork;
    }
}