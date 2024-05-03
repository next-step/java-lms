package nextstep.sessions.domain;

import java.time.LocalDate;

public class Session {

	private final Long id;
	private final SessionInfo sessionInfo;
	private final Charge charge;
	private final Enrollment enrollment;
	private final SessionDate sessionDate;

	public Session(final Long id, String title, Long creatorId,
	               ChargeStatus chargeStatus, int price,
	               int capacity, SessionStatus sessionStatus,
	               LocalDate startDate, LocalDate endDate) {
		this(id, new SessionInfo(title, creatorId, null),
				new Charge(chargeStatus, price),
				new Enrollment(sessionStatus, capacity),
				new SessionDate(new StartedAt(startDate), new EndedAt(endDate)));
	}

	public Session(final Long id, SessionInfo sessionInfo, Charge charge, Enrollment enrollment, SessionDate sessionDate) {
		this.id = id;
		this.sessionInfo = sessionInfo;
		this.charge = charge;
		this.enrollment = enrollment;
		this.sessionDate = sessionDate;
	}

	public String getTitle() {
		return sessionInfo.getTitle();
	}

	public Long getCreatorId() {
		return sessionInfo.getCreatorId();
	}

	public String getChargeStatus() {
		return charge.getStatus().name();
	}

	public int getPrice() {
		return charge.getPrice();
	}

	public LocalDate getStartedAt() {
		return sessionDate.getStartedAt();
	}

	public LocalDate getEndedAt() {
		return sessionDate.getEndedAt();
	}

	public String getSessionStatus() {
		return enrollment.getSessionStatus().name();
	}

	public int getCapacity() {
		return enrollment.getCapacity();
	}
}
