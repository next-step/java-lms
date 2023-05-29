package nextstep.courses.domain;

public enum SessionState {

    PREPARING("준비중", true),
    PROGRESSING("진행중", false),
    FINISH("종료", false);

    private final String desc;
    private final boolean availableManualChangeSession;


    SessionState(
            String desc,
            boolean availableManualChangeSession
    ) {
        this.desc = desc;
        this.availableManualChangeSession = availableManualChangeSession;
    }

    // 추가모집 상태변경 가능 -> 모집상태가 종료이면서, 강의 시작후 7일
//    public boolean isAllowAdditionalRecruitment(RecruitmentState recruitmentState,  LocalDate now) {
//
//
//    }


    public boolean isAvailableManualChangeSession() {
        return availableManualChangeSession;
    }

}
