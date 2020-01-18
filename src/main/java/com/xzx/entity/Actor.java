package com.xzx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value = "演员实体")
public class Actor {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column actor_table.id
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column actor_table.name
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column actor_table.gender
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private String gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column actor_table.occupation
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private String occupation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column actor_table.constellation
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private String constellation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column actor_table.birthday
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column actor_table.location
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private String location;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column actor_table.act_age
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private Integer actAge;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column actor_table.act_style
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private String actStyle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column actor_table.popularity
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private Integer popularity;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column actor_table.id
     *
     * @return the value of actor_table.id
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column actor_table.id
     *
     * @param id the value for actor_table.id
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column actor_table.name
     *
     * @return the value of actor_table.name
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column actor_table.name
     *
     * @param name the value for actor_table.name
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column actor_table.gender
     *
     * @return the value of actor_table.gender
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column actor_table.gender
     *
     * @param gender the value for actor_table.gender
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column actor_table.occupation
     *
     * @return the value of actor_table.occupation
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column actor_table.occupation
     *
     * @param occupation the value for actor_table.occupation
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column actor_table.constellation
     *
     * @return the value of actor_table.constellation
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public String getConstellation() {
        return constellation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column actor_table.constellation
     *
     * @param constellation the value for actor_table.constellation
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setConstellation(String constellation) {
        this.constellation = constellation == null ? null : constellation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column actor_table.birthday
     *
     * @return the value of actor_table.birthday
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column actor_table.birthday
     *
     * @param birthday the value for actor_table.birthday
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column actor_table.location
     *
     * @return the value of actor_table.location
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column actor_table.location
     *
     * @param location the value for actor_table.location
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column actor_table.act_age
     *
     * @return the value of actor_table.act_age
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public Integer getActAge() {
        return actAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column actor_table.act_age
     *
     * @param actAge the value for actor_table.act_age
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setActAge(Integer actAge) {
        this.actAge = actAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column actor_table.act_style
     *
     * @return the value of actor_table.act_style
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public String getActStyle() {
        return actStyle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column actor_table.act_style
     *
     * @param actStyle the value for actor_table.act_style
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setActStyle(String actStyle) {
        this.actStyle = actStyle == null ? null : actStyle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column actor_table.popularity
     *
     * @return the value of actor_table.popularity
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public Integer getPopularity() {
        return popularity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column actor_table.popularity
     *
     * @param popularity the value for actor_table.popularity
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
}