package nextstep.users.domain;

public enum NsUserType {

    USER, COACH, WOOTECO, WOOTECAM;

    public boolean isCoach() {
        return this == COACH;
    }

    public boolean isSelected() {
        return this == WOOTECO || this == WOOTECAM;
    }
}
