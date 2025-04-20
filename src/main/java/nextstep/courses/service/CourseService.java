package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.session.SessionEntityImageMap;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.factory.CourseFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final SessionRepository sessionRepository;
    private final SessionImageRepository sessionImageRepository;
    private final CourseFactory courseFactory;

    public CourseService(CourseRepository courseRepository, SessionRepository sessionRepository, SessionImageRepository sessionImageRepository, CourseFactory courseFactory) {
        this.courseRepository = courseRepository;
        this.sessionRepository = sessionRepository;
        this.sessionImageRepository = sessionImageRepository;
        this.courseFactory = courseFactory;
    }

    public void createCourse(String title, Long creatorId) {
        Course course = new Course(title, creatorId);
        courseRepository.save(course.toCourseEntity());
    }

    @Transactional
    public void deleteCourse(long courseId) throws IOException {
        List<SessionEntity> sessionEntities = sessionRepository.findAllByCourseId(courseId);
        SessionEntityImageMap sessionEntityImageMap = new SessionEntityImageMap();
        for (SessionEntity sessionEntity : sessionEntities) {
            sessionEntityImageMap.add(
                sessionEntity,
                sessionImageRepository.findAllBySessionId(Long.parseLong(sessionEntity.getId()))
            );
        }
        Course course = courseFactory.create(courseRepository.findById(courseId), sessionEntityImageMap);
        course.delete();
    }
}
