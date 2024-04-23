package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.Session;
import nextstep.courses.factory.SessionFactory;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.dto.RequestSessionDto;
import nextstep.courses.entity.ImageEntity;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.error.exception.NotExistImage;
import nextstep.courses.error.exception.NotExistSession;
import nextstep.courses.infrastructure.CourseRepository;
import nextstep.courses.infrastructure.ImageRepository;
import nextstep.courses.infrastructure.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class lmsService {

    private final CourseRepository courseRepository;

    private final SessionRepository sessionRepository;

    private final ImageRepository imageRepository;

    public lmsService(CourseRepository courseRepository,
        SessionRepository sessionRepository,
        ImageRepository imageRepository) {
        this.courseRepository = courseRepository;
        this.sessionRepository = sessionRepository;
        this.imageRepository = imageRepository;
    }

    public Session registerSession(RequestSessionDto requestSessionDto) {
        Course course = courseRepository.findById(requestSessionDto.getCourseId());
        SessionEntity sessionEntity = sessionRepository.findById(
                requestSessionDto.getSessionId())
            .orElseThrow(() -> new NotExistSession(requestSessionDto.getSessionId()));
        ImageEntity imageEntity = imageRepository.findById(sessionEntity.getImageId())
            .orElseThrow(() -> new NotExistImage(sessionEntity.getImageId()));

        Image image = new Image(imageEntity);
        Session session = course.registerSession(SessionFactory.createSession(sessionEntity,
                image,
                SessionType.valueOf(requestSessionDto.getSessionType())),
            requestSessionDto.getPayment());

        sessionRepository.updateRegistrationCount(SessionEntity.from(session, image));
        return session;
    }
}
