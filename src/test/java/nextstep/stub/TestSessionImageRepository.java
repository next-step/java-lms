package nextstep.stub;

import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.entity.SessionImageEntity;

import java.util.List;

public class TestSessionImageRepository implements SessionImageRepository {
    private int saveCalled = 0;
    private int findAllBySessionIdCalled = 0;
    private final Long saveResult;
    private final List<SessionImageEntity> findAllBySessionIdResult;

    public TestSessionImageRepository() {
        this(null, null);
    }

    public TestSessionImageRepository(List<SessionImageEntity> findAllBySessionIdResult) {
        this(null, findAllBySessionIdResult);
    }

    public TestSessionImageRepository(Long saveResult, List<SessionImageEntity> findAllBySessionIdResult) {
        this.saveResult = saveResult;
        this.findAllBySessionIdResult = findAllBySessionIdResult;
    }

    @Override
    public Long save(SessionImageEntity sessionImageEntity) {
        saveCalled++;
        return saveResult;
    }

    @Override
    public List<SessionImageEntity> findAllBySessionId(Long sessionId) {
        findAllBySessionIdCalled++;
        return findAllBySessionIdResult;
    }

    public int getSaveCalled() {
        return saveCalled;
    }

    public int getFindAllBySessionIdCalled() {
        return findAllBySessionIdCalled;
    }
}
