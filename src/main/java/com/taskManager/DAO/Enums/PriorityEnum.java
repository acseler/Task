package com.taskManager.DAO.Enums;

/**
 * Created by boduill on 11.03.16.
 */
public enum PriorityEnum {
    LOW("Low"), MEDIUM("Medium"), HIGH("High");

    private String value;

    private PriorityEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
