package nextstep.courses.service;

import nextstep.courses.domain.ApprovalStatus;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.Student;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcStudentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.JdbcUserRepository;

import java.util.List;


public class SessionService {
    private JdbcUserRepository userRepository;
    private JdbcSessionRepository sessionRepository;
    private JdbcStudentRepository studentsRepository;

    public void approve(Long nsUserId, Long sessionId) {
        NsUser user = userRepository.findById(nsUserId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 userId"));
        Session session = findSessionWithStudentsById(sessionId);
        Student student = session.approve(user);
        studentsRepository.save(student);
    }

    public void disapprove(Long nsUserId, Long sessionId) {
        NsUser user = userRepository.findById(nsUserId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 userId"));
        Session session = findSessionWithStudentsById(sessionId);
        Student student = session.disapprove(user);
        studentsRepository.save(student);
    }

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
