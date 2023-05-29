package nextstep.sessions.domain;

import java.util.Objects;

import nextstep.sessions.exception.AlreadySignUpException;
import nextstep.sessions.exception.CapacityNumberException;
import nextstep.sessions.exception.NotRecruitingException;
import nextstep.sessions.exception.NumberFullException;
import nextstep.sessions.type.StatusType;

public class Enrollment {

	private StatusType statusType;

	private final int capacity;

	private Students students;

	public Enrollment(StatusType statusType, int capacity, Students students) {
		if (capacity < 0) {
			throw new CapacityNumberException("최대 수강 인원은 음수일 수 없습니다.");
		}
		this.statusType = statusType;
		this.capacity = capacity;
		this.students = students;
	}

	public Student enroll(Student student, Students students) {
		if (!this.statusType.isRecruiting()) {
			throw new NotRecruitingException("모집중인 강의가 아닙니다.");
		}
		if (this.students.isFull(this.capacity)) {
			throw new NumberFullException("정원이 가득찼습니다.");
		}
		if (this.students.contains(student)) {
			throw new AlreadySignUpException("이미 수강신청한 유저입니다.");
		}

		this.students = students;
		this.students.add(student);

		return student;
	}

	public void changeStatusType(StatusType statusType) {
		this.statusType = StatusType.of(statusType);
	}

	public StatusType getStatusType() {
		return statusType;
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
		return capacity == that.capacity && statusType == that.statusType && Objects.equals(students, that.students);
	}

	@Override
	public int hashCode() {
		return Objects.hash(statusType, capacity, students);
	}

	@Override
	public String toString() {
		return "Enrollment[" +
			"statusType=" + statusType +
			", capacity=" + capacity +
			", students=" + students +
			']';
	}
}
