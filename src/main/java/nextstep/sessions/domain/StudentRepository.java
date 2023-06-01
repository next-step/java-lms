package nextstep.sessions.domain;

import java.util.List;

public interface StudentRepository {

	int save(Student student);

	List<Student> findBySessionId(long sessionId);

	Student findBySessionIdAndNsUserId(long sessionId, long nsUserId);
}
