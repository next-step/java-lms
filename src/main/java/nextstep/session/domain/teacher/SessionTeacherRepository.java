package nextstep.session.domain.teacher;

import java.util.List;

public interface SessionTeacherRepository {
    Long saveTeacher(Long sessionId, Long nsUserId);

    List<SessionTeacher> getTeachers(Long sessionId);
}
