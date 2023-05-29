package nextstep.courses.domain.ragistration;

public interface RegistrationRepository {
    int save(Registration registration);

    Registration findByCourseIdAndSessionIdAndUserId(long courseId, long sessionId, long userId);

    int update(Registration registration);
}
