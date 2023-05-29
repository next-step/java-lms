package nextstep.users.domain.enums;

public enum UserType {
    GUEST("일반"),
    INSTRUCTOR("강사"),
    ADMIN("관리자");


    private String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
