package nextstep.session.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.session.StudentAlreadyEnrolledException;
import org.junit.jupiter.api.Test;

public class StudentsTest {

    @Test
    public void 학생_등록() throws StudentAlreadyEnrolledException {
        Students students = new Students();
        Student student = new Student(1L, "맥북이");
        students.add(student);

        assertThat(students.isAlreadyEnrolled(student)).isTrue();

    }

    @Test
    public void 이미_등록한_학생인지_체크() throws StudentAlreadyEnrolledException {
        Students students = new Students();
        Student student = new Student(1L, "장갑이");
        students.add(student);
        assertThatThrownBy(() -> {
            students.add(student);
        }).isInstanceOf(StudentAlreadyEnrolledException.class).hasMessageContaining("이미 등록된 학생입니다");
    }
}
