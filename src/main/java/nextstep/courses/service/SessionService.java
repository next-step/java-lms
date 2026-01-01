package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.courses.repository.EnrollmentRepository;
import nextstep.courses.repository.ImageFileRepository;
import nextstep.courses.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Long createSession(ImageFile imageFile,
                              SessionPeriod period,
                              SessionRecruitingStatus recruitingStatus,
                              SessionProgressStatus progressStatus,
                              EnrollmentRule enrollmentRule) {

        ImageFile savedImageFile = imageFileRepository.save(imageFile);

        Session session = new Session(savedImageFile, period, recruitingStatus, progressStatus, enrollmentRule);

        return sessionRepository.save(session);
    }

    @Transactional(readOnly = true)
    public Session findSession(Long id) {
        return sessionRepository.findById(id);
    }

    @Transactional
    public void enroll(Long sessionId, Long memberId, Money money) {
        Session session = findSession(sessionId);

        Enrollment enrollment = new Enrollment(memberId, sessionId);

        session.enroll(enrollment, money);

        enrollmentRepository.save(enrollment);
    }
}
