package com.suma.acs.entity;

import com.suma.acs.dao.QueryField;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    @QueryField
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
