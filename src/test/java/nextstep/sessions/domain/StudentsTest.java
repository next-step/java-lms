package nextstep.sessions.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StudentsTest {
    @Test
    void register() {
        Students students = new Students(3);

        // before register(..)
        assertThat(students.countOfStudents()).isEqualTo(0);

        students.register(new Student(1L, 1L));

        // after register(..)
        assertThat(students.countOfStudents()).isEqualTo(1);
    }

    @Test
    void registerException() {
        Students students = new Students(3);

        students.register(new Student(1L, 1L));
        students.register(new Student(2L, 1L));
        students.register(new Student(3L, 1L));

        assertThatThrownBy(() -> students.register(new Student(4L, 1L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이 강의의 현재 수강 신청 인원: (3)명, 최대 수강 인원: (3)명이므로 현재 마감이 된 상태입니다.");
    }
}
