package ru.magazine.util;

import ru.magazine.entity.NCAttributeType;

/**
 * Created by petka on 07.03.2017.
 *
 * @author Evgeniy Tupikov
 */
public final class ConvertToAttributeType {

    public static NCAttributeType getAttributeType(String type) {
        if (type.equals("date")) {
            return NCAttributeType.DATE;
        } else if (type.equals("number")) {
            return NCAttributeType.NUMBER;
        } else if (type.equals("reference")) {
            return NCAttributeType.REFERENCE;
        } else if (type.equals("string")) {
            return NCAttributeType.STRING;
        } else {
            return NCAttributeType.STRING;
        }
    }

}
