package nextstep.courses.domain;

public enum ApplyStatus {
    APPLYING("신청 중"),
    APPROVAL("승인"),
    REFUSAL("거절");

    private final String description;

    ApplyStatus(String description) {
        this.description = description;
    }
}
