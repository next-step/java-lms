package nextstep.courses.domain.course.session;

public interface ApplicantsRepository {
    Applicants findAllBySessionId(Long SessionId);
}
