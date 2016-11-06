package com.wanguqu;

/**
 * Created by sun on 2016/11/2.
 */

public class MarketSelectData {
    private int id;
    private String name;
    private String phone;
    private String email;

    public MarketSelectData(int i, String name, String s, String concat) {
        setId(i);
        setName(name);
        setPhone(s);
        setEmail(concat);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}