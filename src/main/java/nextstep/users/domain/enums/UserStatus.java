package nextstep.users.domain.enums;

import java.util.Arrays;

public enum UserStatus {
    NOT_SELECTED("비선발"),
    SELECTED_FOR_FREE("무료 강의 선발"),
    SELECTED_FOR_PAID("유료 강의 선발");

    private String description;

    UserStatus(String description) {
        this.description = description;
    }

    public boolean isNotSelected() {
        return this == NOT_SELECTED;
    }

    public static UserStatus of(String userStatus) {
        return Arrays.stream(values())
                .filter(u -> u.toString().equals(userStatus))
                .findFirst()
                .orElse(null);
    }
}