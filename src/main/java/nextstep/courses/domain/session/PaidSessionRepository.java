package nextstep.courses.domain.session;

public interface PaidSessionRepository {
    int save(Long courseId, PaidSession session);

    PaidSession findById(Long id);
}
