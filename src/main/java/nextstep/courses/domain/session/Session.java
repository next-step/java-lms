package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session implements Enrollment {
	private Long id;

	private SessionState sessionState;

	private int numberOfStudent;

	private SessionImage sessionImage;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	public int getNumberOfStudent() {
		return numberOfStudent;
	}

	public Session(Long id, SessionState sessionState, int numberOfStudent, SessionImage sessionImage, LocalDateTime startDate, LocalDateTime endDate) {
		this.id = id;
		this.sessionState = sessionState;
		this.numberOfStudent = numberOfStudent;
		this.sessionImage = sessionImage;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public void enroll(Payment payment) {
		if(!sessionState.equals(SessionState.RECRUITING)) {
			throw new IllegalStateException("모집 중인 강의만 수강 신청 가능합니다.");
		}

		this.numberOfStudent++;
	}

	public void end() {
		this.sessionState = SessionState.END;
	}
}
