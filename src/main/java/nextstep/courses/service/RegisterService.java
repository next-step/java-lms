package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.Student;
import nextstep.courses.infrastructure.JdbcStudentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.JdbcUserRepository;


public class RegisterService {
    private JdbcUserRepository userRepository;
    private JdbcStudentRepository studentsRepository;
    private SessionService sessionService;

    public void register(String userStringId, Long sessionId) {
        NsUser user = userRepository.findByUserId(userStringId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 userId"));
        Session session = sessionService.findSessionWithStudentsById(sessionId);
        Student student = session.add(user);
        studentsRepository.save(student);
    }
}
