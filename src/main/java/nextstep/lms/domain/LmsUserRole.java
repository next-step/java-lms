package nextstep.lms.domain;

public enum LmsUserRole {
    ADMIN, NORMAL;

    public boolean isAdmin() {
        return this == ADMIN;
    }

    public boolean isNotAdmin() {
        return this != ADMIN;
    }
}
