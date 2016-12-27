package ru.magazine.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by petka on 29.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public class NCParam implements Serializable {
    private String ncObjectId;
    private String ncAttributeId;
    private String stringValue;
    private Date dateValue;
    private Double doubleValue;
    private String referenceValue;

    public NCParam() {
    }

    public NCParam(String ncObjectId, String ncAttributeId) {
        this.ncObjectId = ncObjectId;
        this.ncAttributeId = ncAttributeId;
    }

    public NCParam(String ncObjectId, String ncAttributeId, String stringValue) {
        this.ncObjectId = ncObjectId;
        this.ncAttributeId = ncAttributeId;
        this.stringValue = stringValue;
    }

    public NCParam(String ncObjectId, String ncAttributeId, Date dateValue) {
        this.ncObjectId = ncObjectId;
        this.ncAttributeId = ncAttributeId;
        this.dateValue = dateValue;
    }

    public NCParam(String ncObjectId, String ncAttributeId, Double doubleValue) {
        this.ncObjectId = ncObjectId;
        this.ncAttributeId = ncAttributeId;
        this.doubleValue = doubleValue;
    }

    public NCParam(String ncObjectId, String ncAttributeId, String stringValue, Date dateValue, Double doubleValue, String referenceValue) {
        this.ncObjectId = ncObjectId;
        this.ncAttributeId = ncAttributeId;
        this.stringValue = stringValue;
        this.dateValue = dateValue;
        this.doubleValue = doubleValue;
        this.referenceValue = referenceValue;
    }

    public String getNcObjectId() {
        return ncObjectId;
    }

    public void setNcObjectId(String ncObjectId) {
        this.ncObjectId = ncObjectId;
    }

    public String getNcAttributeId() {
        return ncAttributeId;
    }

    public void setNcAttributeId(String ncAttributeId) {
        this.ncAttributeId = ncAttributeId;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public String getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(String referenceValue) {
        this.referenceValue = referenceValue;
    }
}
