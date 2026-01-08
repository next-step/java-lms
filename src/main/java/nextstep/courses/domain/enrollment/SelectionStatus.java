package nextstep.courses.domain.enrollment;

public enum SelectionStatus {
    WAITING("대기"),
    APPROVED("승인"),
    REJECTED("취소");

    private final String description;

    SelectionStatus(String description) {
        this.description = description;
    }
}
