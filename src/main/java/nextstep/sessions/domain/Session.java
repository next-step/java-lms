package nextstep.sessions.domain;

import nextstep.image.domain.Image;

import java.time.LocalDate;

public class Session {

	private Long id;
	private String title;
	private Long courseId;
	private SessionDate sessionDate;
	private Image image;
	private SessionType sessionType;
	private Integer maximumAttendeeNumber;
	private Integer attendeeNumber;
	private Integer tuitionFee;
	private SessionStatus sessionStatus;

	public Session(final Long id, final String title, final Long courseId, final LocalDate startedAt, final LocalDate endedAt, final SessionType sessionType, final Integer maximumAttendeeNumber, final Integer attendeeNumber, final Integer tuitionFee) {
		this(id, title, courseId, new SessionDate(new StartedAt(startedAt), new EndedAt(endedAt)), null, sessionType, maximumAttendeeNumber, attendeeNumber, tuitionFee, SessionStatus.READY);
	}

	public Session(final Long id, final String title, final Long courseId, final SessionDate sessionDate, final Image image, final SessionType sessionType, final Integer maximumAttendeeNumber, final Integer attendeeNumber, final Integer tuitionFee, final SessionStatus sessionStatus) {
		validateSessionMaximumAttendeeNumber(sessionType, maximumAttendeeNumber, attendeeNumber);
		this.id = id;
		this.title = title;
		this.courseId = courseId;
		this.sessionDate = sessionDate;
		this.image = image;
		this.sessionType = sessionType;
		this.maximumAttendeeNumber = maximumAttendeeNumber;
		this.attendeeNumber = attendeeNumber;
		this.tuitionFee = tuitionFee;
		this.sessionStatus = sessionStatus;
	}

	private void validateSessionMaximumAttendeeNumber(final SessionType sessionType, final int maximumAttendeeNumber, final int attendeeNumber) {
		if (sessionType.equals(SessionType.PAID) && maximumAttendeeNumber < attendeeNumber) {
			throw new IllegalArgumentException("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.");
		}
	}

	public boolean isPaidSession() {
		return sessionType.equals(SessionType.PAID);
	}

	public Integer getTuitionFee() {
		return tuitionFee;
	}

	public Long getId() {
		return id;
	}

	public void changeSessionStatus(final SessionStatus sessionStatus) {
		this.sessionStatus = sessionStatus;
	}

	public void validateOpeningSession() {
		if (!sessionStatus.equals(SessionStatus.OPENED)) {
			throw new IllegalArgumentException("강의 모집 상태가 아닙니다. 수강 신청이 불가능합니다.");
		}
	}
}
