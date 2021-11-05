package com.netcracker.db.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Client {
    private Integer id;
    private String fullName;
    private Date birthDate;
    private SexEnum sex;
    private String passportData;

    public Client(Integer id, String fullName, Date birthDate, SexEnum sex, String passportData) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.passportData = passportData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public Integer getAge() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(new Date()));
        return (d2 - d1) / 10000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id) && Objects.equals(fullName, client.fullName) &&
                Objects.equals(birthDate, client.birthDate) && sex == client.sex &&
                Objects.equals(passportData, client.passportData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, birthDate, sex, passportData);
    }
}
