package nextstep.courses.domain.session.repository;

import nextstep.courses.record.SessionRecord;

public interface SessionRepository {

    int save(SessionRecord sessionRecord);

    SessionRecord findById(Long id);

}
