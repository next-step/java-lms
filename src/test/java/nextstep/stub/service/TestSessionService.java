package nextstep.stub.service;

import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.factory.SessionFactory;
import nextstep.courses.service.SessionImageService;
import nextstep.courses.service.SessionService;
import nextstep.stub.factory.TestSessionFactory;
import nextstep.stub.repository.TestSessionRepository;

public class TestSessionService extends SessionService {
    private int deleteSessionsCalled = 0;

    public TestSessionService() {
        this(new TestSessionRepository(), new TestSessionFactory(), new TestSessionImageService());
    }

    public TestSessionService(
        SessionRepository sessionRepository,
        SessionFactory sessionFactory,
        SessionImageService sessionImageService
    ) {
        super(sessionRepository, sessionFactory, sessionImageService);
    }

    @Override
    public void deleteSessions(long courseId) {
        deleteSessionsCalled++;
    }

    public int getDeleteSessionsCalled() {
        return deleteSessionsCalled;
    }
}
