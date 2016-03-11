package com.taskManager.DAO.Enums;

/**
 * Created by boduill on 11.03.16.
 */
public enum TaskStatus {
    IN_PROCESS("In process"), DONE("Done");

    private String value;

    private TaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
