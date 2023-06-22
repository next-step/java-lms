package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.Student;
import nextstep.courses.infrastructure.JdbcStudentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.JdbcUserRepository;


public class ApproveService {
    private JdbcUserRepository userRepository;
    private JdbcStudentRepository studentsRepository;
    private SessionService sessionService;


}
