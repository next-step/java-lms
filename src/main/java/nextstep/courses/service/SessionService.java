package nextstep.courses.service;

import lombok.RequiredArgsConstructor;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.info.basic.SessionThumbnail;
import nextstep.courses.dto.ImageDto;
import nextstep.courses.dto.SessionDto;
import nextstep.courses.infrastructure.ImageRepository;
import nextstep.courses.infrastructure.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import nextstep.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final ImageRepository imageRepository;
    private final PaymentService paymentService;
    private final UserService userService;

    @Transactional
    public void enroll(Long sessionId, String userId, String paymentId) {
        SessionDto sessionDto = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));

        Session session = new Session(sessionDto.getId(), sessionDto.getTitle(),
                sessionDto.getStartDate().atStartOfDay(), sessionDto.getEndDate().atStartOfDay(),
                getThumbnail(sessionDto.getId()),
                sessionDto.getSessionType(), sessionDto.getMaximumEnrollment(),
                0);

        NsUser user = userService.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Payment payment = null;
        if (session.isPaid()) {
            payment = paymentService.payment(paymentId);
        }

        session.enroll(user, payment);
        sessionRepository.update(sessionDto);
    }

    public SessionThumbnail getThumbnail(Long sessionId) {
        ImageDto imageDto = imageRepository.findBySessionId(sessionId);
        if (imageDto == null) {
            throw new IllegalArgumentException("존재하지 않는 이미지입니다.");
        }

        return new SessionThumbnail(imageDto.getFileName(), imageDto.getFileSize(),
                imageDto.getWidth(), imageDto.getHeight());
    }
} 