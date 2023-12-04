package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private Long courseId;
    private Long generation;
    private CoverImage coverImage;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SessionStatus sessionStatus;
    private SessionPaymentInfo sessionPaymentInfo;

    public Session(Long courseId
            , CoverImage coverImage
            , LocalDateTime startedAt
            , LocalDateTime finishedAt
            , SessionStatus sessionStatus
            , SessionPaymentInfo sessionPaymentInfo) {
        this(0L, courseId, 0L, coverImage, startedAt, finishedAt, sessionStatus, sessionPaymentInfo);
    }

    public Session
            (Long id
                    , Long courseId
                    , Long generation
                    , CoverImage coverImage
                    , LocalDateTime startedAt
                    , LocalDateTime finishedAt
                    , SessionStatus sessionStatus
                    , SessionPaymentInfo sessionPaymentInfo) {
        this.id = id;
        this.courseId = courseId;
        this.generation = generation;
        this.coverImage = coverImage;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.sessionStatus = sessionStatus;
        this.sessionPaymentInfo = sessionPaymentInfo;
    }

    public NsUserSession enroll(Payment payment) throws CannotEnrollException {
        checkStatus();
        sessionPaymentInfo.check(payment);
        return new NsUserSession(payment.getSessionId(), payment.getNsUserId());
    }

    private void checkStatus() throws CannotEnrollException {
        if (!sessionStatus.isRecruiting()) {
            throw new CannotEnrollException("강의가 모집중인 상태가 아닙니다.");
        }
    }
}
