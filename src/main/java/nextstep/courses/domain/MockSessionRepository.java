package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static nextstep.courses.domain.SessionStatus.RECRUITING;

public class MockSessionRepository implements SessionRepository {

    private static final Session S1 = new SessionBuilder()
            .withRegister(new RegisterBuilder()
                    .withStatus(RECRUITING)
                    .withMaxRegisterCount(2)
                    .withStudent(new NsUser())
                    .build()
            )
            .build();

    private static final Session S2 = new SessionBuilder()
            .withRegister(new RegisterBuilder()
                    .withStatus(RECRUITING)
                    .withMaxRegisterCount(3)
                    .withStudent(new NsUser())
                    .withStudent(new NsUser())
                    .build()
            )
            .build();

    private static final Map<Long, Session> sessions = new HashMap<>() {{
        sessions.put(1L, S1);
        sessions.put(2L, S2);
    }};

    @Override
    public Optional<Session> findById(Long id) {
        return Optional.ofNullable(sessions.get(id));
    }
}
