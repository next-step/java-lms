package nextstep.courses.domain;

import java.util.Set;

public class Cart {

    private final Set<Session> sessions;

    public Cart(Set<Session> sessions) {
        this.sessions = sessions;
    }

}
