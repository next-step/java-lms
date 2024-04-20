package nextstep.courses.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.Sessions;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.course.CourseEntity;
import nextstep.courses.infrastructure.course.CourseRepository;

@Service("CourseService")
public class CourseService {

    private final CourseRepository courseRepository;
    private final SessionService sessionService;

    public CourseService(final CourseRepository courseRepository, final SessionService sessionService) {
        this.courseRepository = courseRepository;
        this.sessionService = sessionService;
    }

    @Transactional
    public Long save(final Course course) {
        final CourseEntity courseEntity = CourseEntity.fromDomain(course);

        return courseRepository.save(courseEntity);
    }

    public Course findById(final Long courseId) {
        final CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(NoSuchElementException::new);
        final Sessions sessions = sessionService.findAllByCourseId(courseId);

        final Course course = courseEntity.toDomain();
        course.assignSessions(sessions);

        return course;
    }

    @Transactional
    public void addNewSession(final Long courseId, final Long sessionId) {
        final Course course = findById(courseId);
        final Session session = sessionService.findById(sessionId);

        course.addNewSession(session);
        sessionService.update(session);
    }
}
