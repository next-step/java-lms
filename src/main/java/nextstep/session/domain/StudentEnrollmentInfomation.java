package nextstep.session.domain;

public class StudentEnrollmentInfomation{
	private long id;
	private long enrollmentId;
	private long studentId;

	public StudentEnrollmentInfomation(long id, long enrollmentId, long studentId) {
		this.id = id;
		this.enrollmentId = enrollmentId;
		this.studentId = studentId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getStudentId() {
		return studentId;
	}

}
