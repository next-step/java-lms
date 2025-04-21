package nextstep.stub.factory;

import nextstep.courses.domain.session.image.ImageHandler;
import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.courses.factory.SessionImageFactory;

import java.util.List;

public class TestSessionImageFactory extends SessionImageFactory {
    private final SessionImage createSessionImageResult;
    private final SessionImages createSessionImagesResult;
    private int createSessionImageCalled = 0;
    private int createSessionImagesCalled = 0;

    public TestSessionImageFactory() {
        this(new TestImageHandler(), null);
    }

    public TestSessionImageFactory(SessionImage createSessionImageResult) {
        this(new TestImageHandler(), createSessionImageResult);
    }

    public TestSessionImageFactory(ImageHandler imageHandler, SessionImage createSessionImageResult) {
        this(imageHandler, createSessionImageResult, null);
    }

    public TestSessionImageFactory(ImageHandler imageHandler, SessionImage createSessionImageResult, SessionImages createSessionImagesResult) {
        super(imageHandler);
        this.createSessionImageResult = createSessionImageResult;
        this.createSessionImagesResult = createSessionImagesResult;
    }

    @Override
    public SessionImage createSessionImage(SessionImageEntity sessionImageEntity) {
        createSessionImageCalled++;
        return createSessionImageResult;
    }

    @Override
    public SessionImages createSessionImages(List<SessionImageEntity> sessionImageEntities) {
        createSessionImagesCalled++;
        return createSessionImagesResult;
    }

    public int getCreateSessionImageCalled() {
        return createSessionImageCalled;
    }

    public int getCreateSessionImagesCalled() {
        return createSessionImagesCalled;
    }
}
