package nextstep.sessions.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Sessions {

    private List<Session> sessionList;

    public List<Session> getPossibleSessionList() {
        return sessionList.stream()
                .filter(session -> session.isPossibleToEnroll())
                .collect(Collectors.toList());
    }
}
