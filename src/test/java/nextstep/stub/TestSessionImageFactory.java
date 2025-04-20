package nextstep.stub;

import nextstep.courses.domain.session.image.ImageHandler;
import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.courses.factory.SessionImageFactory;

import java.io.IOException;

public class TestSessionImageFactory extends SessionImageFactory {
    private int createCalled = 0;
    private final SessionImage createResult;

    public TestSessionImageFactory() {
        this(new TestImageHandler(), null);
    }

    public TestSessionImageFactory(SessionImage createResult) {
        this(new TestImageHandler(), createResult);
    }

    public TestSessionImageFactory(ImageHandler imageHandler, SessionImage createResult) {
        super(imageHandler);
        this.createResult = createResult;
    }

    @Override
    public SessionImage create(SessionImageEntity sessionImageEntity) throws IOException {
        createCalled++;
        return createResult;
    }

    public int getCreateCalled() {
        return createCalled;
    }
}
