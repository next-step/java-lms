package nextstep.stub.factory;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionEntityImageMap;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.courses.factory.SessionFactory;

import java.util.List;

public class TestSessionFactory extends SessionFactory {
    private int createSessionCalled = 0;
    private int createSessionsCalled = 0;
    private final Session createSessionResult;
    private final Sessions createSessionsResult;

    public TestSessionFactory() {
        this(null);
    }

    public TestSessionFactory(Session createSessionResult) {
        this(createSessionResult, null);
    }

    public TestSessionFactory(Session createSessionResult, Sessions createSessionsResult) {
        super(new TestSessionImageFactory());
        this.createSessionResult = createSessionResult;
        this.createSessionsResult = createSessionsResult;
    }

    @Override
    public Session createSession(SessionEntity sessionEntity, List<SessionImageEntity> sessionImageEntities) {
        createSessionCalled++;
        return createSessionResult;
    }

    @Override
    public Sessions createSessions(SessionEntityImageMap sessionEntityImageMap) {
        createSessionsCalled++;
        return createSessionsResult;
    }

    public int getCreateSessionCalled() {
        return createSessionCalled;
    }

    public int getCreateSessionsCalled() {
        return createSessionsCalled;
    }
}
