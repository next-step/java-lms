package nextstep.courses.domain;

import java.util.*;

public class Session {

    private final JoinUsers joinUsers;
    private final Image coverImage;
    private final SessionStatus status;
    private final Period period;

    public Session() {
        this(new ArrayList<>());
    }

    public Session(List<JoinUser> joinUsers) {
        this.joinUsers = new JoinUsers(joinUsers);
        this.coverImage = new Image();
        this.status = SessionStatus.RECRUITING;
        this.period = new Period();
    }

    public void join(JoinUser joinUser) {
        validRecruiting();
        joinUsers.add(joinUser);
    }

    private void validRecruiting() {
        if (!status.isRecruiting()) {
            throw new IllegalStateException("강의가 모집중이 아닙니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        return Objects.equals(joinUsers, session.joinUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joinUsers);
    }
}
