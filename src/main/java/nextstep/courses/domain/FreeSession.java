package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class FreeSession extends Session {
	private static final String SESSION_NOT_RECRUITING = "해당 강의는 현재 모집 중이 아닙니다.";


	public static FreeSession of(SessionDate sessionDate) {
		return new FreeSession(sessionDate, null);
	}

	// 객체를 생성하고 반환하는 정적 팩토리 메소드
	public static FreeSession of(SessionDate sessionDate, CoverImageInfo coverImageInfo) {
		return new FreeSession(sessionDate, coverImageInfo);
	}

	private FreeSession(SessionDate sessionDate, CoverImageInfo coverImageInfo) {
		super(sessionDate, coverImageInfo);
	}

	@Override
	public void enroll(Payment payment) {
		if (sessionStatus.isStatusNotRecruiting()) {
			numberOfStudents++;
			return;
		}
		throw new IllegalStateException(SESSION_NOT_RECRUITING);
	}
}
