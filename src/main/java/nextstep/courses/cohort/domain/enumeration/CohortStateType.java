package nextstep.courses.cohort.domain.enumeration;

public enum CohortStateType {
    PREPARE("준비중"),
    RECRUIT("모집중"),
    RECRUIT_END("모집마감"),
    ACTIVE("진행중"),
    END("종료"),

    ;

    private final String desc;

    CohortStateType(String desc) {
        this.desc = desc;
    }

}
