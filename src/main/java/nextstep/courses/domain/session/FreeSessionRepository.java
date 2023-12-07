package nextstep.courses.domain.session;

public interface FreeSessionRepository {
    int save(Long courseId, FreeSession session);

    FreeSession findById(Long id);
}
