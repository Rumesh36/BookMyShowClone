
package com.alpha.theaterService.dto;

import com.alpha.theaterService.entity.Adress;

public class CreateTheaterDto {

    private String name;
    private String description;
    private String city;
    private long contactNum;
    private String mailid;
    private int totalscreens;
    private Adress address;

    public CreateTheaterDto() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getContactNum() {
        return contactNum;
    }

    public void setContactNum(long contactNum) {
        this.contactNum = contactNum;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public int getTotalscreens() {
        return totalscreens;
    }

    public void setTotalscreens(int totalscreens) {
        this.totalscreens = totalscreens;
    }

    public Adress getAddress() {
        return address;
    }

    public void setAddress(Adress address) {
        this.address = address;
    }


}
