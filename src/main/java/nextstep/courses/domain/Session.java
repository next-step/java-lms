package nextstep.courses.domain;

import nextstep.users.domain.Student;
import nextstep.users.domain.Students;

import java.util.Objects;

public class Session {
    private Long id;
    private String title;
    private String cover;
    private int cardinalNumber;
    private Cost cost;
    private SessionRegistration sessionRegistration;

    Session(String title, String cover, int cardinalNumber, Cost cost, State state, int maxUser) {
        this(0L, title, cover, cardinalNumber, cost, state, maxUser);
    }

    Session(Long id, String title, String cover, int cardinalNumber, Cost cost, State state, int maxUser) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.cardinalNumber = cardinalNumber;
        this.cost = cost;
        this.sessionRegistration = new SessionRegistration(state, maxUser);
    }

    public static Session of(Long id, String title, String cover, int cardinalNumber, Cost cost, State state, int maxUser) {
        return new Session(id, title, cover, cardinalNumber, cost, state, maxUser);
    }

    public static Session of(String title, String cover, int cardinalNumber, Cost cost, State state, int maxUser) {
        return new Session(title, cover, cardinalNumber, cost, state, maxUser);
    }

    public Students enroll(Student student) {
        return sessionRegistration.register(student);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public int getCardinalNumber() {
        return cardinalNumber;
    }

    public Cost getCost() {
        return cost;
    }

    public SessionRegistration getSessionRegistration() {
        return sessionRegistration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return cardinalNumber == session.cardinalNumber && Objects.equals(id, session.id) && Objects.equals(title, session.title) && Objects.equals(cover, session.cover) && cost == session.cost && Objects.equals(sessionRegistration, session.sessionRegistration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, cover, cardinalNumber, cost, sessionRegistration);
    }
}
