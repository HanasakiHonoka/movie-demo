package com.xzx.entity;

import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(value = "编剧实体")
public class Scenarist {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scenarist_table.id
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scenarist_table.name
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scenarist_table.gender
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private String gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scenarist_table.occupation
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private String occupation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scenarist_table.constellation
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private String constellation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scenarist_table.birthday
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private Date birthday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scenarist_table.location
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    private String location;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scenarist_table.id
     *
     * @return the value of scenarist_table.id
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scenarist_table.id
     *
     * @param id the value for scenarist_table.id
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scenarist_table.name
     *
     * @return the value of scenarist_table.name
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scenarist_table.name
     *
     * @param name the value for scenarist_table.name
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scenarist_table.gender
     *
     * @return the value of scenarist_table.gender
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scenarist_table.gender
     *
     * @param gender the value for scenarist_table.gender
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scenarist_table.occupation
     *
     * @return the value of scenarist_table.occupation
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scenarist_table.occupation
     *
     * @param occupation the value for scenarist_table.occupation
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scenarist_table.constellation
     *
     * @return the value of scenarist_table.constellation
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public String getConstellation() {
        return constellation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scenarist_table.constellation
     *
     * @param constellation the value for scenarist_table.constellation
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setConstellation(String constellation) {
        this.constellation = constellation == null ? null : constellation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scenarist_table.birthday
     *
     * @return the value of scenarist_table.birthday
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scenarist_table.birthday
     *
     * @param birthday the value for scenarist_table.birthday
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scenarist_table.location
     *
     * @return the value of scenarist_table.location
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scenarist_table.location
     *
     * @param location the value for scenarist_table.location
     *
     * @mbg.generated Fri Dec 13 18:45:29 CST 2019
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}