package nextstep.courses.domain;

import java.util.Objects;

public class Session {
    private String title;
    private String cover;
    private Cost cost;
    private State state;
    private int maxUser;

    Session(String title, String cover, Cost cost, State state, int maxUser) {
        this.title = title;
        this.cover = cover;
        this.cost = cost;
        this.state = state;
        this.maxUser = maxUser;
    }

    public static Session of(String title, String cover, Cost cost, State state, int maxUser) {
        return new Session(title, cover, cost, state, maxUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return maxUser == session.maxUser && Objects.equals(title, session.title) && Objects.equals(cover, session.cover) && cost == session.cost && state == session.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cover, cost, state, maxUser);
    }
}
