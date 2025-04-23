package nextstep.stub.repository;

import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.entity.SessionEntity;

import java.util.List;

public class TestSessionRepository implements SessionRepository {
    private final Long saveResult;
    private final SessionEntity findByIdResult;
    private final List<SessionEntity> findAllByCourseIdResult;
    private int saveCalled = 0;
    private int deleteCalled = 0;

    public TestSessionRepository() {
        this(null, null, null);
    }

    public TestSessionRepository(Long saveResult, SessionEntity findByIdResult, List<SessionEntity> findAllByCourseIdResult) {
        this.saveResult = saveResult;
        this.findByIdResult = findByIdResult;
        this.findAllByCourseIdResult = findAllByCourseIdResult;
    }

    @Override
    public Long save(SessionEntity sessionEntity) {
        saveCalled++;
        return saveResult;
    }

    @Override
    public SessionEntity findById(Long id) {
        return findByIdResult;
    }

    @Override
    public List<SessionEntity> findAllByCourseId(Long courseId) {
        return findAllByCourseIdResult;
    }

    @Override
    public void delete(Long sessionId) {
        deleteCalled++;
    }

    public int getSaveCalled() {
        return saveCalled;
    }

    public int getDeleteCalled() {
        return deleteCalled;
    }
}
