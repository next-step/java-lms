package nextstep.courses.domain.type;

public enum ApplyStatus {

    APPLYING("신청 중"),
    APPROVAL("승인"),
    REFUSAL("거절");

    private String description;

    ApplyStatus(String description) {
        this.description = description;
    }

}
