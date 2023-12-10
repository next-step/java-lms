package nextstep.lms.enums;

public enum StudentStatusEnum {
    APPLIED("신청"),
    SELECTED("선발"),
    NON_SELECTED("미선발");

    private String value;

    StudentStatusEnum(String value) {
        this.value = value;
    }
}
