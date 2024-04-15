package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.Generation;
import nextstep.courses.domain.session.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service("managerEnrollService")
public class ManagerEnrollService {

  @Transactional
  public Course createCourse(long courseId, long generationId, String courseTitle, long createdBy) {
    return new Course(courseId, new Generation(generationId), courseTitle, createdBy, LocalDateTime.now(), LocalDateTime.now());
  }

  @Transactional
  public Session createSession(long sessionId, SessionInfo sessionInfo, CoverImage coverImage) {
    return new Session(
        sessionId,
        new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)),
        sessionInfo,
        coverImage
    );
  }

  @Transactional
  public void addSession(Course course, Set<Session> sessions) {
    course.addSessions(sessions);
  }
}
