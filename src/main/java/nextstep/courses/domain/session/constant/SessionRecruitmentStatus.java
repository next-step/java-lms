package nextstep.courses.domain.session.constant;

public enum SessionRecruitmentStatus {

    NOT_RECRUITING("비모집중"),
    RECRUITING("모집중");

    private String status;

    SessionRecruitmentStatus(String status) {
        this.status = status;
    }


}
