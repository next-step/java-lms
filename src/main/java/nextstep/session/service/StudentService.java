package nextstep.session.service;

import nextstep.common.domain.DeleteHistory;
import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.session.domain.Student;
import nextstep.session.domain.Students;
import nextstep.users.domain.NsUser;

public interface StudentService {

    Students findBySessionId(long sessionId);

    Long save(Student student);

    DeleteHistory delete(NsUser requestStudent, Student student);

    DeleteHistoryTargets deleteAll(Students targetStudents, NsUser requestUser);
}
