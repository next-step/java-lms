package nextstep.sessions.domain;

public class UserCount{
    private final int userCount;

    public UserCount(int userCount) {
        this.userCount = userCount;
    }

    public boolean biggerThan(Integer o) {
        if (this.userCount > o) {
            return true;
        } return false;
    }
}
