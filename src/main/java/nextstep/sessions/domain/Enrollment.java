package nextstep.sessions.domain;

import java.util.Objects;

import nextstep.sessions.exception.AlreadySignUpException;
import nextstep.sessions.exception.CapacityNumberException;
import nextstep.sessions.exception.NotRecruitingException;
import nextstep.sessions.exception.NumberFullException;
import nextstep.sessions.exception.ProgressStatusException;
import nextstep.sessions.type.ProgressType;
import nextstep.sessions.type.RecruitStatusType;

public class Enrollment {

	private ProgressType progressType;

	private RecruitStatusType recruitStatusType;

	private final int capacity;

	private final Students students;

	public Enrollment(ProgressType progressType, RecruitStatusType recruitStatusType, int capacity, Students students) {
		if (capacity < 0) {
			throw new CapacityNumberException("최대 수강 인원은 음수일 수 없습니다.");
		}
		this.progressType = progressType;
		this.recruitStatusType = recruitStatusType;
		this.capacity = capacity;
		this.students = students;
	}

	public Student enroll(Student student) {
		if (!this.recruitStatusType.isRecruiting()) {
			throw new NotRecruitingException("모집중인 강의가 아닙니다.");
		}
		if (!this.progressType.isProgressing()) {
			throw new ProgressStatusException("준비중이거나 이미 종료된 강의입니다.");
		}
		if (this.students.isFull(this.capacity)) {
			throw new NumberFullException("정원이 가득찼습니다.");
		}
		if (this.students.contains(student)) {
			throw new AlreadySignUpException("이미 수강신청한 유저입니다.");
		}

		this.students.add(student);

		return student;
	}

	public void changeProgressStatusType(ProgressType progressType) {
		this.progressType = ProgressType.of(progressType);
	}

	public void changeRecruitingStatusType(RecruitStatusType recruitStatusType) {
		this.recruitStatusType = RecruitStatusType.of(recruitStatusType);
	}

	public boolean contains(Student student) {
		return this.students.contains(student);
	}

	public ProgressType getProgressType() {
		return progressType;
	}

	public RecruitStatusType getRecruitingStatusType() {
		return recruitStatusType;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Enrollment that = (Enrollment)o;
		return capacity == that.capacity && progressType == that.progressType && recruitStatusType == that.recruitStatusType && Objects.equals(students, that.students);
	}

	@Override
	public int hashCode() {
		return Objects.hash(progressType, recruitStatusType, capacity, students);
	}

	@Override
	public String toString() {
		return "Enrollment[" +
			"progressType=" + progressType +
			", recruitingStatusType=" + recruitStatusType +
			", capacity=" + capacity +
			", students=" + students +
			']';
	}
}
