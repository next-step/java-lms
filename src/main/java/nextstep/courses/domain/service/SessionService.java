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
import nextstep.payments.domain.Payment;
import nextstep.payments.repository.PaymentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final CoverImageRepository coverImageRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public SessionService(SessionRepository sessionRepository,
                          CoverImageRepository coverImageRepository,
                          CourseRepository courseRepository,
                          EnrollmentRepository enrollmentRepository,
                          UserRepository userRepository,
                          PaymentRepository paymentRepository) {
        this.sessionRepository = sessionRepository;
        this.coverImageRepository = coverImageRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    public Session findById(Long id) {
        SessionRecord sessionRecord = sessionRepository.findById(id);
        CoverImage saveCoverImage = coverImageRepository.findById(sessionRecord.getCoverImageId());
        Course saveCourse = courseRepository.findById(sessionRecord.getCourseId());
        List<EnrollmentRecord> enrollmentRecords = enrollmentRepository.findBySessionId(id);
        Enrollments enrollments = toEnrollments(enrollmentRecords);

        return sessionRecord.toSession(saveCourse, saveCoverImage, enrollments);
    }

    @Transactional
    public int save(Session session) {
        coverImageRepository.save(session.getCoverImage());
        return sessionRepository.save(session.toSessionRecord());
    }

    @Transactional
    public int saveEnrollment(Enrollment enrollment, Payment payment) {
        SessionRecord sessionRecord = sessionRepository.findById(enrollment.getSessionId());
        List<EnrollmentRecord> enrollmentRecords = enrollmentRepository.findBySessionId(enrollment.getSessionId());
        Session session = sessionRecord.toSession(null, null, toEnrollments(enrollmentRecords));
        session.addEnrollment(enrollment, payment);

        paymentRepository.save(payment);

        return enrollmentRepository.save(enrollment);
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
