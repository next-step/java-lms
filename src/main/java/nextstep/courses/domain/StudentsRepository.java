package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public interface StudentsRepository {
    int save(Long sessionId, Long userId);

    List<NsUser> findAllBySessionId(Long id);
}
