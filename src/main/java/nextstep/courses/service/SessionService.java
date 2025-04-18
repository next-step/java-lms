package nextstep.courses.service;

import lombok.RequiredArgsConstructor;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionId;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.enrollment.FreeEnrollment;
import nextstep.courses.domain.session.enrollment.PaidEnrollment;
import nextstep.courses.domain.session.info.SessionInfo;
import nextstep.courses.domain.session.info.basic.SessionBasicInfo;
import nextstep.courses.domain.session.info.basic.SessionThumbnail;
import nextstep.courses.domain.session.info.detail.SessionDetailInfo;
import nextstep.courses.domain.session.info.detail.SessionPeriod;
import nextstep.courses.domain.session.info.detail.SessionPrice;
import nextstep.courses.dto.ImageDto;
import nextstep.courses.dto.SessionDto;
import nextstep.courses.infrastructure.ImageRepository;
import nextstep.courses.infrastructure.SessionEnrollmentRepository;
import nextstep.courses.infrastructure.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import nextstep.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final SessionEnrollmentRepository sessionEnrollmentRepository;
    private final ImageRepository imageRepository;
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
        }

        session.enroll(user, payment);
        
        SessionDto updatedSessionDto = SessionDto.of(session);
        updatedSessionDto.setTimeStampForUpdate();
        sessionEnrollmentRepository.save(sessionId, user.getId());
    }

    private Session findSession(Long sessionId) {
        SessionDto sessionDto = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));

        return createSessionFromDto(sessionDto);
    }

    private Session createSessionFromDto(SessionDto sessionDto) {
        SessionBasicInfo sessionBasicInfo = new SessionBasicInfo(sessionDto.getTitle(), getThumbnail(sessionDto.getId()));
        SessionDetailInfo sessionDetailInfo = getSessionDetailInfo(sessionDto);
        SessionInfo sessionInfo = new SessionInfo(sessionBasicInfo, sessionDetailInfo);

        Enrollment enrollment = getEnrollment(sessionDto);
        SessionId entityId = new SessionId(sessionDto.getId(), sessionDto.getCourseId());
        return new Session(entityId, sessionInfo, enrollment);
    }

    private SessionDetailInfo getSessionDetailInfo(SessionDto sessionDto) {
        SessionPeriod sessionPeriod = new SessionPeriod(sessionDto.getStartDate(), sessionDto.getEndDate());
        SessionPrice sessionPrice = new SessionPrice(sessionDto.getSessionType(), 0);
        return new SessionDetailInfo(sessionPeriod, sessionPrice);
    }

    private SessionThumbnail getThumbnail(Long sessionId) {
        ImageDto imageDto = imageRepository.findBySessionId(sessionId);
        if (imageDto == null) {
            throw new IllegalArgumentException("존재하지 않는 이미지입니다.");
        }

        return new SessionThumbnail(imageDto.getFileName(), imageDto.getFileSize(),
                imageDto.getWidth(), imageDto.getHeight());
    }

    private Enrollment getEnrollment(SessionDto sessionDto) {
        SessionType sessionType = sessionDto.getSessionType();
        List<Long> enrolledUserIds = sessionEnrollmentRepository.findUserIdsBySessionId(sessionDto.getId());
        List<NsUser> enrolledUsers = findEnrolledUsersByIds(enrolledUserIds);
        SessionStatus sessionStatus = sessionDto.getStatus();

        if (sessionType.isPaid()) {
            int maximumEnrollment = sessionDto.getMaximumEnrollment();
            return new PaidEnrollment(maximumEnrollment, enrolledUsers, sessionStatus);
        }

        return new FreeEnrollment(enrolledUsers, sessionStatus);
    }

    private List<NsUser> findEnrolledUsersByIds(List<Long> enrolledUserIds) {
        return enrolledUserIds.stream()
                .map(userId -> userService.findByUserId(userId.toString())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")))
                .collect(Collectors.toList());
    }
}