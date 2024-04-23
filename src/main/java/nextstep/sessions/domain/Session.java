package nextstep.sessions.domain;

import java.time.LocalDate;

public class Session {

	private final  SessionInfo sessionInfo;
	private final  Charge charge;
	private final Enrollment enrollment;
	private final  SessionDate sessionDate;

	public Session(String title, Long creatorId,
				   ChargeStatus chargeStatus, int price,
				   int capacity, SessionStatus sessionStatus,
				   LocalDate startDate, LocalDate endDate) {
		this(new SessionInfo(title, creatorId, null),
				new Charge(chargeStatus, price),
				new Enrollment(sessionStatus, capacity),
				new SessionDate(new StartedAt(startDate), new EndedAt(endDate)));
	}

	public Session(SessionInfo sessionInfo, Charge charge, Enrollment enrollment, SessionDate sessionDate) {
		this.sessionInfo = sessionInfo;
		this.charge = charge;
		this.enrollment = enrollment;
		this.sessionDate = sessionDate;
	}
}
