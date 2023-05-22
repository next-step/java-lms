package nextstep.qna.domain;

public enum DeleteStatus {
    DELETED("삭제"),

    NOT_DELETED("삭제되지 않음");

    private final String desc;

    DeleteStatus(String desc) {
        this.desc = desc;
    }

    public boolean isDeleted() {
        return this == DELETED;
    }

}
