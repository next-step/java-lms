package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private final List<Session> sessions = new ArrayList<>();

    private Sessions() {
    }

    public static Sessions from() {
        return new Sessions();
    }
}
