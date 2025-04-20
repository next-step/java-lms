package nextstep.stub;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.courses.factory.SessionImageFactory;
import nextstep.courses.factory.SessionImagesFactory;

import java.io.IOException;
import java.util.List;

public class TestSessionImagesFactory extends SessionImagesFactory {

    private int createCalled = 0;
    private final SessionImages createResult;

    public TestSessionImagesFactory() {
        this(new TestSessionImageFactory(), null);
    }

    public TestSessionImagesFactory(SessionImages createResult) {
        this(new TestSessionImageFactory(), createResult);
    }

    public TestSessionImagesFactory(SessionImageFactory sessionImageFactory, SessionImages createResult) {
        super(sessionImageFactory);
        this.createResult = createResult;
    }

    @Override
    public SessionImages create(List<SessionImageEntity> sessionImageEntities) throws IOException {
        createCalled++;
        return createResult;
    }

    public int getCreateCalled() {
        return createCalled;
    }

}
