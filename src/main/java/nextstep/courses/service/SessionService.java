package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.courses.repository.EnrollmentRepository;
import nextstep.courses.repository.ImageFileRepository;
import nextstep.courses.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ImageFileRepository imageFileRepository;
    private final EnrollmentRepository enrollmentRepository;

    public SessionService(SessionRepository sessionRepository,
                          ImageFileRepository imageFileRepository,
                          EnrollmentRepository enrollmentRepository) {
        this.sessionRepository = sessionRepository;
        this.imageFileRepository = imageFileRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    public Long createSession(ImageFiles imageFiles,
                              SessionPeriod period,
                              SessionRecruitingStatus recruitingStatus,
                              SessionProgressStatus progressStatus,
                              EnrollmentRule enrollmentRule) {

        Session session = new Session(period, recruitingStatus, progressStatus, enrollmentRule);

        Session savedSession = sessionRepository.save(session);

        for(ImageFile imageFile : imageFiles.getImageFiles()) {
            savedSession.addImageFile(imageFile);
            imageFileRepository.save(imageFile);
        }

        return savedSession.getId();
    }

    @Transactional(readOnly = true)
    public Session findSession(Long id) {
        Session session = sessionRepository.findById(id);

        List<ImageFile> imageFiles = imageFileRepository.findBySessionId(id);

        session.loadImageFiles(imageFiles);

        List<Enrollment> enrollments = enrollmentRepository.findBySessionId(id);

        session.loadEnrollments(enrollments);

        return session;
    }

    @Transactional
    public void enroll(Long sessionId, Long studentId, Money money) {
        Session session = findSession(sessionId);

        Enrollment enrollment = new Enrollment(studentId, sessionId);

        session.enroll(enrollment, money);

        enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void selectEnrollment(Long sessionId, Long enrollmentId) {
        Session session = findSession(sessionId);

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);

        Enrollment updatedEnrollment = session.selectEnrollment(enrollment.getId());

        enrollmentRepository.update(updatedEnrollment);
    }

    @Transactional
    public void approveEnrollment(Long sessionId, Long enrollmentId) {
        Session session = findSession(sessionId);

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);

        Enrollment updatedEnrollment = session.approveEnrollment(enrollment.getId());

        enrollmentRepository.update(updatedEnrollment);
    }

    @Transactional
    public void cancelEnrollment(Long sessionId, Long enrollmentId) {
        Session session = findSession(sessionId);

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);

        Enrollment updatedEnrollment = session.cancelEnrollment(enrollment.getId());

        enrollmentRepository.update(updatedEnrollment);
    }
}
