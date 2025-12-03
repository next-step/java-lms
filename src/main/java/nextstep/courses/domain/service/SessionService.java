package nextstep.courses.domain.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.CoverImageRepository;
import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.Enrollments;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.repository.EnrollmentRepository;
import nextstep.courses.domain.session.repository.SessionRepository;
import nextstep.courses.record.EnrollmentRecord;
import nextstep.courses.record.SessionRecord;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final CoverImageRepository coverImageRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;

    public SessionService(SessionRepository sessionRepository,
                          CoverImageRepository coverImageRepository,
                          CourseRepository courseRepository,
                          EnrollmentRepository enrollmentRepository,
                          UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.coverImageRepository = coverImageRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
    }

    public Session findById(Long id) {
        SessionRecord sessionRecord = sessionRepository.findById(id);
        CoverImage saveCoverImage = coverImageRepository.findById(sessionRecord.getCoverImageId());
        Course saveCourse = courseRepository.findById(sessionRecord.getCourseId());
        List<EnrollmentRecord> enrollmentRecords = enrollmentRepository.findBySessionId(id);
        Enrollments enrollments = toEnrollments(enrollmentRecords);

        return sessionRecord.toSession(saveCourse, saveCoverImage, enrollments);
    }

    private Enrollments toEnrollments(List<EnrollmentRecord> enrollmentRecords) {
        Enrollments enrollments = new Enrollments();

        for (EnrollmentRecord enrollmentRecord : enrollmentRecords) {
            Optional<NsUser> user = userRepository.findById(enrollmentRecord.getUserId());
            enrollments.add(enrollmentRecord.toEnrollment(user.orElse(null)));
        }

        return enrollments;
    }


}
