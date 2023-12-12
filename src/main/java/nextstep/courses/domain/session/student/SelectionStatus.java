package nextstep.courses.domain.session.student;

public enum SelectionStatus {

    WAITING("대기"),
    SELECTION("승인"),
    DENIAL("거절");

    private String description;

    SelectionStatus(String description) {
        this.description = description;
    }
}
