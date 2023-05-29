package nextstep.users.domain;

public enum UserType {
    STUDENT("학생", false),
    INSTRUCTOR("강사", true),
    GUEST("게스트", false);

    private final String desc;
    private final boolean allowOpenSession;

    UserType(String desc, boolean allowOpenSession) {
        this.desc = desc;
        this.allowOpenSession = allowOpenSession;
    }

    public boolean isAllowOpenSession() {
        return allowOpenSession;
    }

    public boolean isGuest() {
        return this == GUEST;
    }
}
