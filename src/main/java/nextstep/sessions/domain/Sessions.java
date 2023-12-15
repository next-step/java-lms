package nextstep.sessions.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Sessions {

    private List<Session> sessionList;

    public Sessions(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    public List<Session> getPossibleSessionList() {
        return sessionList.stream()
                .filter(session -> session.isRecruitingStatus())
                .collect(Collectors.toList());
    }
}
