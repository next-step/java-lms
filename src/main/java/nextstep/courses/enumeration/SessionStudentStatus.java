package nextstep.courses.enumeration;

public enum SessionStudentStatus {

    REQUESTED("요청"),
    APPROVED("승인"),
    CANCELED("취소");

    private final String value;

    SessionStudentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
