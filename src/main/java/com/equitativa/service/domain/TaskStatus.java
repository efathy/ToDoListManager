package com.equitativa.service.domain;

public enum TaskStatus {
    TO_DO("To Do"), IN_PROGRESS("In Progress"), DONE("Done");

    private String value;

    TaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
