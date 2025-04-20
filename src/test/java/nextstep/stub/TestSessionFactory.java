package nextstep.stub;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.image.ImageHandler;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.factory.SessionFactory;

public class TestSessionFactory extends SessionFactory {
    private int createCalled = 0;
    private Session createResult = null;

    public TestSessionFactory() {
    }

    public TestSessionFactory(Session createResult) {
        this.createResult = createResult;
    }

    @Override
    public Session create(SessionEntity sessionEntity) {
        createCalled++;
        return createResult;
    }

    public int getCreateSessionCalled() {
        return createCalled;
    }
}
