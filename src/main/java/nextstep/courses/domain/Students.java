package nextstep.courses.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Students {
    private final List<Student> students;

    public Students(Student... students) {
        this(Arrays.asList(students));
    }

    public Students(List<Student> students) {
        this.students = students;
    }

    public Optional<Student> findByNsUserId(Long userId) {
        return students.stream().filter(student -> student.nsUserId().equals(userId)).findFirst();
    }

    public Optional<Student> findBySessionId(Long sessionId){
        return students.stream().filter(student -> student.sessionId().equals(sessionId)).findFirst();
    }

    @Override
    public String toString() {
        return "Students{" +
                "nsUserSessions=" + students +
                '}';
    }
}
