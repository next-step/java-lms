package nextstep.sessions.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Students {

	private final List<Student> students;

	public Students() {
		this(new ArrayList<>());
	}

	public Students(List<Student> students) {
		this.students = students;
	}

	public boolean contains(Student student) {
		return this.students.contains(student);
	}

	public int add(Student student) {
		this.students.add(student);
		return this.students.size();
	}

	public boolean isFull(int capacity) {
		return this.students.size() >= capacity;
	}

	public Student last() {
		return this.students.get(this.students.size() - 1);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Students students1 = (Students)o;
		return Objects.equals(students, students1.students);
	}

	@Override
	public int hashCode() {
		return Objects.hash(students);
	}

	@Override
	public String toString() {
		return "Students[" +
			"students=" + students +
			']';
	}
}
