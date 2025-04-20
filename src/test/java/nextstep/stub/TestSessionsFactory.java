package nextstep.stub;

import nextstep.courses.domain.session.SessionEntityImageMap;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.factory.SessionFactory;
import nextstep.courses.factory.SessionsFactory;

public class TestSessionsFactory extends SessionsFactory {
    private final Sessions createResult;
    private int createCalled = 0;

    public TestSessionsFactory() {
        this(new TestSessionFactory(), new Sessions());
    }

    public TestSessionsFactory(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.createResult = new Sessions();
    }

    public TestSessionsFactory(SessionFactory sessionFactory, Sessions createsResult) {
        super(sessionFactory);
        this.createResult = createsResult;
    }

    @Override
    public Sessions create(SessionEntityImageMap sessionEntityImageMap) {
        createCalled++;
        return createResult;
    }

    public int getCreateSessionsCalled() {
        return createCalled;
    }
}
