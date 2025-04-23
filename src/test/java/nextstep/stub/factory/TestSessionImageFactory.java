package nextstep.stub.factory;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.courses.factory.SessionImageFactory;

import java.util.List;

public class TestSessionImageFactory extends SessionImageFactory {
    private final SessionImage createSessionImageResult1;
    private final SessionImage createSessionImageResult2;
    private final SessionImages createSessionImagesResult;

    public TestSessionImageFactory() {
        this(null, null, null);
    }

    public TestSessionImageFactory(SessionImage createSessionImageResult) {
        this(createSessionImageResult, null, null);
    }

    public TestSessionImageFactory(SessionImage createSessionImageResult, SessionImage createSessionImageResult2) {
        this(createSessionImageResult, createSessionImageResult2, null);
    }

    public TestSessionImageFactory(SessionImage createSessionImageResult1, SessionImage createSessionImageResult2, SessionImages createSessionImagesResult) {
        this.createSessionImageResult1 = createSessionImageResult1;
        this.createSessionImageResult2 = createSessionImageResult2;
        this.createSessionImagesResult = createSessionImagesResult;
    }

    @Override
    public SessionImage createSessionImage(String imageUrl, String imageType) {
        return createSessionImageResult2;
    }

    @Override
    public SessionImage createSessionImage(SessionImageEntity sessionImageEntity) {
        return createSessionImageResult1;
    }

    @Override
    public SessionImages createSessionImages(List<SessionImageEntity> sessionImageEntities) {
        return createSessionImagesResult;
    }
}
