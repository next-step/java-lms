package nextstep.stub.factory;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.factory.SessionFactory;

public class TestSessionFactory extends SessionFactory {
    private int createSessionCalled = 0;
    private final Session createSessionResult;

    public TestSessionFactory() {
        this(null);
    }

    public TestSessionFactory(Session createSessionResult) {
        this.createSessionResult = createSessionResult;
    }

    @Override
    public Session createSession(SessionEntity sessionEntity, SessionImages sessionImages) {
        createSessionCalled++;
        return createSessionResult;
    }

    public int getCreateSessionCalled() {
        return createSessionCalled;
    }
}
