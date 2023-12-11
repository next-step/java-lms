package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Students {
    private final List<Student> students;

    private Students(List<Student> students) {
        this.students = students;
    }

    public static Students of(List<Student> students) {
        return new Students(students);
    }

    public static Students of(Student... students) {
        return new Students(List.of(students));
    }

    public static Students of(NsUser... students) {
        return new Students(Arrays.stream(students)
                .map(Student::ofWait)
                .collect(Collectors.toList()));
    }

    public void add(Student student) {
        students.add(student);
    }

    public int size() {
        return students.size();
    }

    public Student orElseThrow(NsUser nsUser) {
        return students.stream()
                .filter(student -> student.sameUser(nsUser))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록하지 않은 수강생입니다."));
    }

    public List<Student> values() {
        return List.copyOf(students);
    }

    @Override
    public String toString() {
        return "Students{" +
                "students=" + students +
                '}';
    }
}
