package nextstep.users.domain.enums;

public enum UserStatus {
    NOT_SELECTED("비선발"),
    SELECTED_FOR_FREE("무료 강의 선발"),
    SELECTED_FOR_PAID("유료 강의 선발");

    private String description;

    UserStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}