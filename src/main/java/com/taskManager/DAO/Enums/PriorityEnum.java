package com.taskManager.DAO.Enums;

/**
 * Created by boduill on 11.03.16.
 */
public enum PriorityEnum {
    LOW(1), MEDIUM(2), HIGH(3);

    private Integer value;

    private PriorityEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
