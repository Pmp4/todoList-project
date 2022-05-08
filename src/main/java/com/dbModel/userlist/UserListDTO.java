package com.dbModel.userlist;

import java.sql.Blob;
import java.sql.Timestamp;

public class UserListDTO {
    private int no;
    private String userId;
    private String pwd;
    private String name;
    private int age;
    private int jno;
//    private Blob profilePhoto;
    private String introduction;
    private Timestamp regdate;

    public UserListDTO(int no, String userId, String pwd, String name, int age, int jno, String introduction, Timestamp regdate) {
        this.no = no;
        this.userId = userId;
        this.pwd = pwd;
        this.name = name;
        this.age = age;
        this.jno = jno;
        this.introduction = introduction;
        this.regdate = regdate;
    }

    public UserListDTO() {
        super();
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getJno() {
        return jno;
    }

    public void setJno(int jno) {
        this.jno = jno;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Timestamp getRegdate() {
        return regdate;
    }

    public void setRegdate(Timestamp regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "UserListDTO{" +
                "no=" + no +
                ", userId='" + userId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", jno=" + jno +
                ", introduction='" + introduction + '\'' +
                ", regdate=" + regdate +
                '}';
    }
}
