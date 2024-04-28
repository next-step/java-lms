package nextstep.sessions.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StudentsTest {
    @Test
    void enroll() {
        Students students = new Students(3);

        // before register(..)
        assertThat(students.countOfStudents()).isEqualTo(0);

        students.enroll(new Student(1L, 1L));

        // after register(..)
        assertThat(students.countOfStudents()).isEqualTo(1);
    }

    @Test
    void enrollException() {
        Students students = new Students(3);

        students.enroll(new Student(1L, 1L));
        students.enroll(new Student(2L, 1L));
        students.enroll(new Student(3L, 1L));

        assertThatThrownBy(() -> students.enroll(new Student(4L, 1L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이 강의의 현재 수강 신청 인원: (3)명, 최대 수강 인원: (3)명이므로 현재 마감이 된 상태입니다.");
    }

    @Test
    void enrollContainException() {
        Students students = new Students(3);

        students.enroll(new Student(1L, 1L));

        assertThatThrownBy(() -> students.enroll(new Student(1L, 1L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이 학생(student)은 이미 수강 신청한 학생입니다.");
    }
}
