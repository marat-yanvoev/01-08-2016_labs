package ru.magazine.entity;

import java.io.Serializable;

/**
 * Created by petka on 29.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public class NCObjectType implements Serializable{
    private String id;
    private String name;
    private String parentId;

    public NCObjectType() {
    }


    public NCObjectType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public NCObjectType(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
