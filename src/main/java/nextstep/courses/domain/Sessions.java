package nextstep.courses.domain;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Sessions {
    private List<Session> sessions;

    public Sessions(List<Session> sessions){
        this.sessions = List.copyOf(sessions);
    }

    public List<Session> sessions(){
        return Collections.unmodifiableList(sessions);
    }

    public Session findById(long id){
        return sessions.stream()
                .filter(session -> session.hasId(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 세션이 존재하지 않습니다."));
    }
}
