package nextstep.courses.domain.session;

import java.time.LocalDateTime;

import nextstep.courses.domain.BaseTimeEntity;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.enums.ApplyStatus;
import nextstep.courses.domain.enums.ProgressStatus;
import nextstep.courses.domain.session.image.Images;
import nextstep.courses.domain.session.registration.SessionRegistration;
import nextstep.users.domain.NsUser;

public class Session extends BaseTimeEntity {
	private final Long id;
	private final String title;
	private final Period period;
	private final ProgressStatus progressStatus;
	private final ApplyStatus applyStatus;
	private final SessionRegistration sessionRegistration;
	private final Course course;
	private final Images images;

	public Session(
		Long id, String title,
		Period period, ProgressStatus progressStatus,
		ApplyStatus applyStatus, SessionRegistration sessionRegistration,
		Course course, Images images, LocalDateTime createdAt, LocalDateTime updatedAt
		) {
		super(createdAt, updatedAt);
		this.id = id;
		this.title = title;
		this.period = period;
		this.progressStatus = progressStatus;
		this.applyStatus = applyStatus;
		this.sessionRegistration = sessionRegistration;
		this.course = course;
		this.images = images;
	}

	public Session(
		Long id, String title, Period period, ProgressStatus progressStatus,
		ApplyStatus applyStatus, SessionRegistration sessionRegistration, Course course, Images images
	) {
		this.id = id;
		this.title = title;
		this.period = period;
		this.progressStatus = progressStatus;
		this.applyStatus = applyStatus;
		this.sessionRegistration = sessionRegistration;
		this.course = course;
		this.images = images;
	}

	public void apply(NsUser nsUser, long amount) {
		sessionRegistration.validateAmount(amount);
		canApply();

		sessionRegistration.register(nsUser, this);
	}

	public void checkCanApprove() {
		sessionRegistration.validateCapacity();
	}

	private void canApply() {
		if ( !applyStatus.isApplying() ) {
			throw new IllegalArgumentException("강의 신청 기간이 아닙니다.");
		}
		if ( progressStatus.isFinish() ) {
			throw new IllegalArgumentException("종료된 강의는 신청할 수 없습니다.");
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

	public ProgressStatus getProgressStatus() {
		return progressStatus;
	}

	public ApplyStatus getApplyStatus() {
		return applyStatus;
	}

	public SessionRegistration getSessionRegistration() {
		return sessionRegistration;
	}

	public Course getCourse() {
		return course;
	}

	public Images getImages() {
		return images;
	}
}
