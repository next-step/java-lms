package nextstep.courses.tobe.domain;

import nextstep.courses.domain.session.Students;
import nextstep.courses.tobe.domain.session.TobeStudents;

public interface TobeStudentsRepository {
    int saveAll(TobeStudents students);
    TobeStudents findAllBySessionId(long sessionId);
}
