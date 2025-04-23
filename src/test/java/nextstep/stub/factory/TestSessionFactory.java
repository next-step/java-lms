package nextstep.stub.factory;

import nextstep.courses.domain.session.Session;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.courses.factory.SessionFactory;

import java.util.List;

public class TestSessionFactory extends SessionFactory {
    private final Session createSessionResult;

    public TestSessionFactory() {
        this(null);
    }

    public TestSessionFactory(Session createSessionResult) {
        super(new TestSessionImageFactory());
        this.createSessionResult = createSessionResult;
    }

    @Override
    public Session createSession(SessionEntity sessionEntity, List<SessionImageEntity> sessionImageEntities) {
        return createSessionResult;
    }
}
