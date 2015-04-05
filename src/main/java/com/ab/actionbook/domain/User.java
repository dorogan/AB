package com.ab.actionbook.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "users")
@SecondaryTables({
        //@SecondaryTable(name = "users_relations", pkJoinColumns = @PrimaryKeyJoinColumn(name = "uid")),
        @SecondaryTable(name = "user_authorization", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id")),
        @SecondaryTable(name = "users_information", pkJoinColumns = @PrimaryKeyJoinColumn(name = "uid"))
})


public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "dateOfReg")
    private Date dateOfReg;

    /*@Column(name = "fid", table = "users_relations")
    private Integer fid;

    @Column(name = "relation", table = "users_relations")
    private Integer relation;*/

    @Column(name = "userrole_id", table = "user_authorization")
    private Integer userrole_id;

    @Column(name = "role", table = "user_authorization")
    private String role;

    //@Column(name = "reg_date", table = "users_information")
    //private Date dateOfRegistry;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "birthday", table = "users_information")
    private Date dateOfBirthday;

    @Column(name = "avatar", table = "users_information")
    private String avatarPath;

    @Column(name = "interests", table = "users_information")
    private String interests;

    @Column(name = "profession", table = "users_information")
    private String profession;

    @Column(name = "phones", table = "users_information")
    private String phone;

    @Column(name = "address", table = "users_information")
    private String address;

    @Column(name = "skype", table = "users_information")
    private String skype;

    public User(String login, String firstname, String lastname, String email, String password) {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public User(){}

    public Integer getUserrole_id() {
        return userrole_id;
    }

    public void setUserrole_id(Integer userrole_id) {
        this.userrole_id = userrole_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfReg() {
        return dateOfReg;
    }

    public void setDateOfReg(Date dateOfReg) {
        this.dateOfReg = dateOfReg;
    }
/*
    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }*/

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }
}
