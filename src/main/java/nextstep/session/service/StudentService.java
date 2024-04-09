package nextstep.session.service;

import nextstep.common.domain.DeleteHistory;
import nextstep.session.domain.Student;
import nextstep.session.domain.Students;

public interface StudentService {

    Students findBySessionId(Long sessionId);

    Long save(Long sessionId, Student student);

    DeleteHistory delete(Long sessionId, Student student);
}
