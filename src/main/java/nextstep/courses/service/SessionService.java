package nextstep.courses.service;

import nextstep.courses.domain.ApprovalStatus;
import nextstep.courses.domain.Session;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcStudentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.JdbcUserRepository;

import java.util.List;


public class SessionService {
    private JdbcUserRepository userRepository;
    private JdbcSessionRepository sessionRepository;
    private JdbcStudentRepository studentsRepository;

    public Session findSessionWithStudentsById(Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        List<NsUser> approvedStudents = studentsRepository.findAllBySessionIdAndApprovalStatus(sessionId, ApprovalStatus.APPROVED);
        List<NsUser> waitStudents = studentsRepository.findAllBySessionIdAndApprovalStatus(sessionId, ApprovalStatus.WAIT);
        return new Session(session, approvedStudents, waitStudents);
    }

    public Session findSessionWithoutStudentsById(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }
}
