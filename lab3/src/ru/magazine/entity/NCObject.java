package ru.magazine.entity;

import java.io.Serializable;

/**
 * Created by petka on 29.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public class NCObject implements Serializable{
    private String id;
    private String name;
    private String parentId;
    private String objectTypeId;

    public NCObject() {
    }

    public NCObject(String id, String name, String objectTypeId) {
        this(id, name, null, objectTypeId);
    }

    public NCObject(String id, String name, String parentId, String objectTypeId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.objectTypeId = objectTypeId;
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

    public String getType() {
        return objectTypeId;
    }

    public void setType(String objectTypeId) {
        this.objectTypeId = objectTypeId;
    }
}
