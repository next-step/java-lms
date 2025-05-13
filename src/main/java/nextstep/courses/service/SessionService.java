package nextstep.courses.service;

import lombok.RequiredArgsConstructor;
import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.enrollment.Enrollments;
import nextstep.courses.domain.session.enrollment.EnrollmentStatus;
import nextstep.courses.domain.session.enrollment.FreeEnrollments;
import nextstep.courses.domain.session.enrollment.PaidEnrollments;
import nextstep.courses.domain.session.info.SessionInfo;
import nextstep.courses.domain.session.info.basic.SessionBasicInfo;
import nextstep.courses.domain.session.info.detail.SessionDetailInfo;
import nextstep.courses.domain.session.info.detail.SessionPeriod;
import nextstep.courses.domain.session.info.detail.SessionPrice;
import nextstep.courses.dto.SessionDto;
import nextstep.courses.infrastructure.SessionEnrollmentRepository;
import nextstep.courses.infrastructure.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import nextstep.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final SessionEnrollmentRepository sessionEnrollmentRepository;
    private final ImageService imageService;
    private final PaymentService paymentService;
    private final UserService userService;

    @Transactional
    public void enroll(Long sessionId, String userId, String paymentId) {
        Session session = findSession(sessionId);
        NsUser user = userService.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Payment payment = null;
        if (session.isPaid()) {
            payment = paymentService.payment(paymentId);
            if (payment == null) {
                throw new IllegalArgumentException("유료 강의는 결제가 필요합니다.");
            }
            session.getInfo().validatePayment(payment);
        }

        Enrollments enrollments = session.createEnrollments();
        enrollments.enroll(user);
        
        SessionDto updatedSessionDto = SessionDto.of(session);
        updatedSessionDto.setTimeStampForUpdate();
        sessionEnrollmentRepository.save(sessionId, user.getId());
    }

    @Transactional
    public void approve(Long sessionId, String userId) {
        Session session = findSession(sessionId);
        NsUser user = userService.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Enrollments enrollments = session.createEnrollments();
        enrollments.approve(user);

        SessionDto updatedSessionDto = SessionDto.of(session);
        updatedSessionDto.setTimeStampForUpdate();
        sessionEnrollmentRepository.updateStatus(sessionId, user.getId(), EnrollmentStatus.ENROLLED);
    }

    private Session findSession(Long sessionId) {
        SessionDto sessionDto = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));

        return createSessionFromDto(sessionDto);
    }

    private Session createSessionFromDto(SessionDto sessionDto) {
        SessionBasicInfo sessionBasicInfo = new SessionBasicInfo(sessionDto.getTitle(),
                imageService.findThumbnailBySessionId(sessionDto.getId()));
        SessionDetailInfo sessionDetailInfo = getSessionDetailInfo(sessionDto);
        SessionInfo sessionInfo = new SessionInfo(
                sessionBasicInfo, 
                sessionDetailInfo, 
                sessionDto.getMaximumEnrollment(),
                sessionDto.getProgressStatus(),
                sessionDto.getRecruitmentStatus()
        );

        SessionId entityId = new SessionId(sessionDto.getId(), sessionDto.getCourseId());
        return new Session(entityId, sessionInfo);
    }

    private SessionDetailInfo getSessionDetailInfo(SessionDto sessionDto) {
        SessionPeriod sessionPeriod = new SessionPeriod(sessionDto.getStartDate(), sessionDto.getEndDate());
        SessionPrice sessionPrice = new SessionPrice(sessionDto.getSessionType(), 0);
        return new SessionDetailInfo(sessionPeriod, sessionPrice);
    }

    private Enrollments getEnrollment(SessionDto sessionDto) {
        SessionType sessionType = sessionDto.getSessionType();
        List<Long> enrolledUserIds = sessionEnrollmentRepository.findUserIdsBySessionId(sessionDto.getId());
        List<NsUser> enrolledUsers = userService.findEnrolledUsersByIds(enrolledUserIds);
        SessionProgressStatus progressStatus = sessionDto.getProgressStatus();
        SessionRecruitmentStatus recruitmentStatus = sessionDto.getRecruitmentStatus();

        if (sessionType.isPaid()) {
            int maximumEnrollment = sessionDto.getMaximumEnrollment();
            return new PaidEnrollments(maximumEnrollment, enrolledUsers, progressStatus, recruitmentStatus);
        }

        return new FreeEnrollments(enrolledUsers, progressStatus, recruitmentStatus);
    }
}