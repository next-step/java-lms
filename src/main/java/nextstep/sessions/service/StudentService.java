package nextstep.sessions.service;

import java.time.LocalDateTime;
import javax.annotation.Resource;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.students.SessionRegistration;
import nextstep.sessions.domain.students.Student;
import nextstep.sessions.domain.students.StudentRepository;
import nextstep.sessions.domain.students.Students;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentService")
public class StudentService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "userRepository")
    private UserRepository nsUserRepository;

    @Resource(name = "studentRepository")
    private StudentRepository studentRepository;

    @Transactional
    public void enrollment(Long sessionId, String userId) {
        Session session = findSessionById(sessionId);
        NsUser user = findNsUserById(userId);
        Students students = studentRepository.findAllBySessionId(sessionId);
        Student student = new Student(session, user, LocalDateTime.now(), null);

        session.enrollment(students, student);
        studentRepository.save(student);
    }

    @Transactional
    public void accept(Long studentId) {
        Student student = findStudentById(studentId);
        Session session = findSessionById(student.getSessionId());
        Students students = studentRepository.findAllBySessionId(student.getSessionId());

        session.accept(students, student);

        studentRepository.update(student);
    }

    @Transactional
    public void reject(Long studentId) {
        Student student = findStudentById(studentId);
        student.reject();

        studentRepository.update(student);
    }

    private Session findSessionById(Long id) {
        return sessionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 수강 신청자입니다."));
    }

    private NsUser findNsUserById(String id) {
        return nsUserRepository.findByUserId(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }
}
