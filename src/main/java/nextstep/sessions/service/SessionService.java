package nextstep.sessions.service;

import java.time.LocalDateTime;
import javax.annotation.Resource;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.students.Student;
import nextstep.sessions.domain.students.StudentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionService")
public class SessionService {

  @Resource(name = "sessionRepository")
  private SessionRepository sessionRepository;

  @Resource(name = "userRepository")
  private UserRepository nsUserRepository;

  @Resource(name = "studentRepository")
  private StudentRepository studentRepository;

  @Transactional
  public void save(String title, String contents, LocalDateTime startDateTime, LocalDateTime endDateTime, byte[] coverImage, int capacity) {
    Session session = new Session(title, contents, startDateTime, endDateTime, coverImage, capacity);
    sessionRepository.save(session);
  }

  @Transactional
  public void enrollment(Long sessionId, String userId) {
    Session session = sessionRepository.findById(sessionId);
    NsUser user = nsUserRepository.findByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

    session.enrollment(new Student(session, user, LocalDateTime.now(), null));
  }

  @Transactional
  public void accept(Long studentId) {
    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 수강 신청자입니다."));
    Session session = sessionRepository.findById(student.getSessionId());

    session.accept(student);

    studentRepository.update(student);
  }

  @Transactional
  public void reject(Long studentId) {
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 수강 신청자입니다."));
    student.reject();

    studentRepository.update(student);
  }

  // start / end각각으로 분리하는 게 아니라 엔티티에 대해 update할 내용을 dto로 받아서
  // 수정하는 방식이라면 내용이 수정되어야 한다
  @Transactional
  public void recruitStart(Long sessionId) {
    Session session = sessionRepository.findById(sessionId);
    session.recruitStart();

    sessionRepository.update(session);
  }

  @Transactional
  public void recruitEnd(Long sessionId) {
    Session session = sessionRepository.findById(sessionId);
    session.recruitEnd();

    sessionRepository.update(session);
  }
}