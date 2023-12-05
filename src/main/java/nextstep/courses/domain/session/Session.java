package nextstep.courses.domain.session;

import java.time.LocalDateTime;

import nextstep.courses.domain.BaseTimeEntity;
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
	private final Long courseId;

	public Session(
		LocalDateTime createdAt, LocalDateTime updatedAt, Long id, String title,
		Period period, Image image, Status status,
		SessionRegistration sessionRegistration, Long courseId
	) {
		super(createdAt, updatedAt);
		this.id = id;
		this.title = title;
		this.period = period;
		this.image = image;
		this.status = status;
		this.sessionRegistration = sessionRegistration;
		this.courseId = courseId;
	}

	public void apply(NsUser nsUser, long amount) {
		sessionRegistration.validate(amount);
		canApply();

		sessionRegistration.register(nsUser);
	}

	private void canApply() {
		if ( !status.isApplying() ) {
			throw new IllegalArgumentException("강의 신청 기간이 아닙니다.");
		}
	}
}
