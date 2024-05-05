package nextstep.courses.domain.student;

public enum StudentType {
    NORMAL("일반"),
    WOOWAHAN_TECH_COURSE_FREE("우아한테크코스(무료)"),
    WOOWAHAN_TECH_CAMP_PRO("우아한테크캠프 Pro(유료)");

    private final String value;

    StudentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
