package nextstep.users.domain;

public enum NsUserType {

    USER, COACH, WOOTECO, WOOTECAM;

    public boolean isCoach() {
        return this == COACH;
    }

    public boolean isNotSelected() {
        return this != WOOTECO && this != WOOTECAM;
    }
}
