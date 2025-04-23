package nextstep.stub.repository;

import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.entity.SessionImageEntity;

import java.util.List;

public class TestSessionImageRepository implements SessionImageRepository {
    private final Long saveResult;
    private final SessionImageEntity findByIdResult;
    private final List<SessionImageEntity> findAllBySessionIdResult;
    private int saveCalled = 0;
    private int deleteCalled = 0;

    public TestSessionImageRepository() {
        this(null, null, List.of());
    }

    public TestSessionImageRepository(List<SessionImageEntity> findAllBySessionIdResult) {
        this(null, null, findAllBySessionIdResult);
    }

    public TestSessionImageRepository(Long saveResult, SessionImageEntity findByIdResult, List<SessionImageEntity> findAllBySessionIdResult) {
        this.saveResult = saveResult;
        this.findByIdResult = findByIdResult;
        this.findAllBySessionIdResult = findAllBySessionIdResult;
    }

    @Override
    public Long save(SessionImageEntity sessionImageEntity) {
        saveCalled++;
        return saveResult;
    }

    @Override
    public SessionImageEntity findById(Long sessionImageId) {
        return findByIdResult;
    }

    @Override
    public List<SessionImageEntity> findAllBySessionId(Long sessionId) {
        return findAllBySessionIdResult;
    }

    @Override
    public void delete(Long sessionImageId) {
        deleteCalled++;
    }

    public int getSaveCalled() {
        return saveCalled;
    }

    public int getDeleteCalled() {
        return deleteCalled;
    }
}
