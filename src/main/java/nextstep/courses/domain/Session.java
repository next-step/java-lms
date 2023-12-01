package nextstep.courses.domain;

import java.time.LocalDateTime;

import nextstep.courses.domain.enums.PaidType;
import nextstep.courses.domain.enums.Status;
import nextstep.users.domain.NsUser;

public class Session extends BaseTimeEntity{
	private Long id;
	private String title;
	private Period period;
	private Image image;
	private Status status;
	private PaidType paidType;
	private Tuition tuition;
	private SessionCapacity maximumCapacity;
	private Students students;


	public Session(
		Long id, String title, Period period, Image image,
		LocalDateTime createdAt, LocalDateTime updatedAt
	) {
		super(createdAt, updatedAt);
		this.id = id;
		this.title = title;
		this.period = period;
		this.image = image;
	}

	public void apply(NsUser nsUser, long amount) {
		if ( !paidType.isFree() ) {
			tuition.isEqual(amount);
			maximumCapacity.isOver(students.number());
		}
		canApply();
		students.add(nsUser);
	}

	public void canApply() {
		if ( !status.isApplying() ) {
			throw new IllegalArgumentException("강의 신청 기간이 아닙니다.");
		}
	}
}
