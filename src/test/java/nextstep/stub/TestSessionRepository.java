package nextstep.stub;

import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.entity.SessionEntity;

import java.util.List;

public class TestSessionRepository implements SessionRepository  {
    private int saveCalled = 0;
    private int findByIdCalled = 0;
    private int findAllByCourseIdCalled = 0;

    private final Long saveResult;
    private final SessionEntity findByIdResult;
    private final List<SessionEntity> findAllByCourseIdResult;

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
        findByIdCalled++;
        return findByIdResult;
    }

    @Override
    public List<SessionEntity> findAllByCourseId(Long courseId) {
        findAllByCourseIdCalled++;
        return findAllByCourseIdResult;
    }

    public int getSaveCalled() {
        return saveCalled;
    }

    public int getFindByIdCalled() {
        return findByIdCalled;
    }

    public int getFindAllByCourseIdCalled() {
        return findAllByCourseIdCalled;
    }
}
