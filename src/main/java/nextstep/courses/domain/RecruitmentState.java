package nextstep.courses.domain;

public enum RecruitmentState {

    RECRUITING("모집중", true),
    END_OF_RECRUITMENT("모집종료", false);

    private final String desc;
    private final boolean availableRecruitment;

    RecruitmentState(
            String desc,
            boolean availableRecruitment
    ) {
        this.desc = desc;
        this.availableRecruitment = availableRecruitment;

    }


    public boolean isAvailableRecruitment() {
        return this.availableRecruitment;
    }

    // 추가모집 상태변경 가능 -> 모집상태가 종료이면서, 강의 시작후 7일
//    public boolean isAllowAdditionalRecruitment(RecruitmentState recruitmentState, SessionPeriod sessionPeriod) {
//
//
//    }


}
