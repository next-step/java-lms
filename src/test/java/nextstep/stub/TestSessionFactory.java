package nextstep.stub;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.image.ImageHandler;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.courses.factory.SessionFactory;
import nextstep.courses.factory.SessionImagesFactory;

import java.util.List;

public class TestSessionFactory extends SessionFactory {
    private int createCalled = 0;
    private Session createResult = null;

    public TestSessionFactory() {
        this(null);
    }

    public TestSessionFactory(Session createResult) {
        super(new TestSessionImagesFactory());
        this.createResult = createResult;
    }

    @Override
    public Session create(SessionEntity sessionEntity, List<SessionImageEntity> sessionImageEntities) {
        createCalled++;
        return createResult;
    }

    public int getCreateSessionCalled() {
        return createCalled;
    }
}
