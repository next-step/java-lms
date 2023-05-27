package nextstep.courses.domain;

public class FreeSession extends Session{
    public FreeSession(String title, String cover, int cardinalNumber, Cost cost, State state, int maxUser) {
        super(title, cover, cardinalNumber, cost, state, maxUser);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
