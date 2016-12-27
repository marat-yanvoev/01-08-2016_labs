package ru.magazine.entity;

import java.io.Serializable;

/**
 * Created by petka on 29.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public class NCAttribute implements Serializable {
    private String id;
    private String name;
    private String objectTypeId;
    private int orderId;
    private NCAttributeType type;
    private Boolean multiple = false;

    public NCAttribute() {
    }


    public NCAttribute(String id, String name, String objectTypeId, int orderId, NCAttributeType type) {
        this(id, name, objectTypeId, orderId, type, false);
    }

    public NCAttribute(String id, String name, String objectTypeId, int orderId, NCAttributeType type, Boolean multiple) {
        this.id = id;
        this.name = name;
        this.objectTypeId = objectTypeId;
        this.orderId = orderId;
        this.type = type;
        this.multiple = multiple;
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

    public String getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(String objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public NCAttributeType getType() {
        return type;
    }

    public void setType(NCAttributeType type) {
        this.type = type;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }
}
