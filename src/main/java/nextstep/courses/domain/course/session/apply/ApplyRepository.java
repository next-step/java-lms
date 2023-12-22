package nextstep.courses.domain.course.session.apply;

import java.util.Optional;

public interface ApplyRepository {
    Applies findAllBySessionId(Long SessionId);

    Apply save(Apply apply);

    Apply update(Apply apply);

    Optional<Apply> findApplyByNsUserIdAndSessionId(Long NsUserId, Long sessionId);
}
