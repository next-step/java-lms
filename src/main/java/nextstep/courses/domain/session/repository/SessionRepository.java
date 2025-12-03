package nextstep.courses.domain.session.repository;


import nextstep.courses.domain.session.Session;
import nextstep.courses.record.SessionRecord;

public interface SessionRepository {

    int save(Session session);

    SessionRecord findById(Long id);

}
