package nextstep.courses.domain.session.enums;

public enum SelectionStatus {

    WAITING("대기"),
    APPROVAL("승인"),
    DENIAL("거절");

    private String description;

    SelectionStatus(String description) {
        this.description = description;
    }
}
