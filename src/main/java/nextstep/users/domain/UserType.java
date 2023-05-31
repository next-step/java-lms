package nextstep.users.domain;

public enum UserType {
    USER, INSTRUCTOR;

    public boolean isInstructor() {
        return this == UserType.INSTRUCTOR;
    }
}
