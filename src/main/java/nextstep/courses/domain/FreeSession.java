package nextstep.courses.domain;

import java.time.LocalDateTime;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.payments.domain.Payment;

public class FreeSession extends Session {
	private static final String SESSION_NOT_RECRUITING = "해당 강의는 현재 모집 중이 아닙니다.";

	public FreeSession(LocalDateTime startDate, LocalDateTime endDate) {
		super(startDate, endDate);
	}

	@Override
	public void enroll(Payment payment) {
		if(sessionStatus == SessionStatus.READY){
			numberOfStudents++;
			return;
		}
		throw new IllegalStateException(SESSION_NOT_RECRUITING);
	}
}
