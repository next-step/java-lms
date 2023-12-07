package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session {
	private Long id;
	private Long courseId;
	private Long generation;
	private CoverImage coverImage;
	private SessionPeriod sessionPeriod;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private SessionStatus sessionStatus;
	private SessionPaymentCondition sessionPaymentCondition;
	private Long userNumber;

	public Session(Long courseId
			, CoverImage coverImage
			, SessionPeriod sessionPeriod
			, SessionStatus sessionStatus
			, SessionPaymentCondition sessionPaymentCondition
			, Long userNumber) {
		this(0L, courseId, 0L, coverImage, sessionPeriod, sessionStatus, sessionPaymentCondition, userNumber);
	}

	public Session(Long id
			, Long courseId
			, Long generation
			, CoverImage coverImage
			, SessionPeriod sessionPeriod
			, SessionStatus sessionStatus
			, SessionPaymentCondition sessionPaymentCondition
			, Long userNumber) {
		validate(courseId);
		this.id = id;
		this.courseId = courseId;
		this.generation = generation;
		this.coverImage = coverImage;
		this.sessionPeriod = sessionPeriod;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
		this.sessionStatus = sessionStatus;
		this.sessionPaymentCondition = sessionPaymentCondition;
		this.userNumber = userNumber;
	}

	private void validate(Long courseId) {
		if (courseId == null || courseId == 0L) {
			throw new IllegalArgumentException("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
		}
	}

	public NsUserSession enroll(Payment payment) throws CannotEnrollException {
		checkStatus();
		sessionPaymentCondition.checkPaidSession(payment, userNumber);
		userNumber++;
		return new NsUserSession(payment.getSessionId(), payment.getNsUserId());
	}

	private void checkStatus() throws CannotEnrollException {
		if (!sessionStatus.isRecruiting()) {
			throw new CannotEnrollException("강의가 모집중인 상태가 아닙니다.");
		}
	}
}
