package nextstep.courses.domain.session;

import java.time.LocalDateTime;

import nextstep.courses.domain.BaseTimeEntity;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.session.image.Image;
import nextstep.courses.domain.enums.Status;
import nextstep.courses.domain.session.registration.SessionRegistration;
import nextstep.users.domain.NsUser;

public class Session extends BaseTimeEntity {
	private final Long id;
	private final String title;
	private final Period period;
	private final Image image;
	private final Status status;
	private final SessionRegistration sessionRegistration;
	private final Course course;

	public Session(
		Long id, String title,
		Period period, Image image, Status status,
		SessionRegistration sessionRegistration, Course course,
		LocalDateTime createdAt, LocalDateTime updatedAt
		) {
		super(createdAt, updatedAt);
		this.id = id;
		this.title = title;
		this.period = period;
		this.image = image;
		this.status = status;
		this.sessionRegistration = sessionRegistration;
		this.course = course;
	}

	public Session(
		Long id, String title,
		Period period, Image image, Status status,
		SessionRegistration sessionRegistration, Course course
	) {
		this.id = id;
		this.title = title;
		this.period = period;
		this.image = image;
		this.status = status;
		this.sessionRegistration = sessionRegistration;
		this.course = course;
	}

	public void apply(NsUser nsUser, long amount) {
		sessionRegistration.validate(amount);
		canApply();

		sessionRegistration.register(nsUser, this);
	}

	private void canApply() {
		if ( !status.isApplying() ) {
			throw new IllegalArgumentException("강의 신청 기간이 아닙니다.");
		}
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Period getPeriod() {
		return period;
	}

	public Image getImage() {
		return image;
	}

	public Status getStatus() {
		return status;
	}

	public SessionRegistration getSessionRegistration() {
		return sessionRegistration;
	}

	public Course getCourse() {
		return course;
	}
}
