package nextstep.courses.domain.session;


import nextstep.courses.record.SessionRecord;

public interface SessionRepository {

    int save(Session session);

    SessionRecord findById(Long id);

}
