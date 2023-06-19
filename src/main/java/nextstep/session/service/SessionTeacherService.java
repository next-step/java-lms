package nextstep.session.service;

import nextstep.session.domain.Session;
import nextstep.session.domain.teacher.SessionTeacher;
import nextstep.session.domain.teacher.SessionTeacherRepository;
import nextstep.session.domain.teacher.SessionTeachers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionTeacherService {

    private final SessionTeacherRepository teacherRepository;


    public SessionTeacherService(SessionTeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public SessionTeachers getTeachersOfSession(Session session) {
        List<SessionTeacher> teachers = teacherRepository.getTeachers(session.getId());
        return new SessionTeachers(teachers);
    }
}
