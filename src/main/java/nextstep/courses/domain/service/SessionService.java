package nextstep.courses.domain.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.CoverImageRepository;
import nextstep.courses.domain.session.Enrollments;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.record.SessionRecord;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final CoverImageRepository coverImageRepository;
    private final CourseRepository courseRepository;

    public SessionService(SessionRepository sessionRepository, CoverImageRepository coverImageRepository, CourseRepository courseRepository) {
        this.sessionRepository = sessionRepository;
        this.coverImageRepository = coverImageRepository;
        this.courseRepository = courseRepository;
    }

    public Session findById(Long id) {

        SessionRecord sessionRecord = sessionRepository.findById(id);
        CoverImage saveCoverImage = coverImageRepository.findById(sessionRecord.getCoverImageId());
        Course saveCourse = courseRepository.findById(sessionRecord.getCourseId());

        return sessionRecord.toSession(saveCourse, saveCoverImage, null);
    }


}
