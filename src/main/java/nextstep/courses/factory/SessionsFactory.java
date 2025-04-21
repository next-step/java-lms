package nextstep.courses.factory;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionEntityImageMap;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.SessionImageEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SessionsFactory {

    private final SessionFactory sessionFactory;

    public SessionsFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Sessions create(SessionEntityImageMap sessionEntityImageMap) throws IOException {
        List<Session> sessions = new ArrayList<>();

        for (Map.Entry<SessionEntity, List<SessionImageEntity>> entry : sessionEntityImageMap.entrySet()) {
            sessions.add(sessionFactory.create(entry.getKey(), entry.getValue()));
        }

        return new Sessions(sessions);
    }
}
