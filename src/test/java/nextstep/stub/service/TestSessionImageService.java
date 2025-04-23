package nextstep.stub.service;

import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.factory.SessionImageFactory;
import nextstep.courses.service.SessionImageService;
import nextstep.stub.repository.TestSessionImageRepository;

public class TestSessionImageService extends SessionImageService {
    int deleteSessionImagesCalled = 0;

    public TestSessionImageService() {
        this(new TestSessionImageRepository(), new SessionImageFactory());
    }

    public TestSessionImageService(SessionImageRepository sessionImageRepository, SessionImageFactory sessionImageFactory) {
        super(sessionImageRepository, sessionImageFactory);
    }

    @Override
    public void deleteSessionImages(long sessionId) {
        deleteSessionImagesCalled++;
    }

    public int getDeleteSessionImagesCalled() {
        return deleteSessionImagesCalled;
    }
}
