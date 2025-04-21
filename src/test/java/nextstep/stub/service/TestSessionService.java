package nextstep.stub.service;

import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.factory.SessionFactory;
import nextstep.courses.service.SessionService;
import nextstep.stub.factory.TestSessionFactory;
import nextstep.stub.repository.TestSessionImageRepository;
import nextstep.stub.repository.TestSessionRepository;

public class TestSessionService extends SessionService {
    public TestSessionService() {
        this(new TestSessionRepository(), new TestSessionImageRepository(), new TestSessionFactory());
    }

    public TestSessionService(SessionRepository sessionRepository, SessionImageRepository sessionImageRepository, SessionFactory sessionFactory) {
        super(sessionRepository, sessionImageRepository, sessionFactory);
    }
}
