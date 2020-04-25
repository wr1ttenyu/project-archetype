package wr1ttenyu.f1nal.study.project.archetype.model.enums;

public enum OrderType {

    TICKET_CHARGE("TICKET_CHARGE"),

    M_PLUS_CREATE("M_PLUS_CREATE");

    private String type;

    OrderType(String type) {
        this.type = type;
    }
}
