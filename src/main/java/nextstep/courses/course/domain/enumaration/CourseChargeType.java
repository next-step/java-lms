package nextstep.courses.course.domain.enumaration;

public enum CourseChargeType {
    FREE("무료강의"),
    PAID("유료강의"),
    ;

    private final String desc;

    CourseChargeType(String desc) {
        this.desc = desc;
    }
}
