package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.repository.CourseRepository;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.users.domain.User;
import nextstep.users.domain.UserRepository;
import nextstep.users.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CourseService {
    private final CourseRepository courseRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, SessionRepository sessionRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public long createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Transactional
    public int updateCourse(Course course) {
        return courseRepository.update(course);
    }

    @Transactional
    public int deleteCourse(Long courseId) {
        return courseRepository.delete(courseId);
    }

    public List<Session> getSessionsByCourseId(Long courseId) {
        return sessionRepository.findSessionsByCourseId(courseId);
    }

    @Transactional
    public void enrollUserToSession(Long sessionId, String userId) {
        Session session = sessionRepository.findById(sessionId);
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        session.enroll(user);
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
}
