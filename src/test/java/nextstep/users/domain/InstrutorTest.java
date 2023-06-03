package nextstep.users.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class InstrutorTest {

    private Instructor instructor;
    @BeforeEach
    void setUp() {
        instructor = new Instructor(1L, 1L);
    }

    @Test
    @DisplayName("수강 여부 승인_exception")
    void approve_wrongSessionId() {

        // 수강생(student_id: 1L, ns_user_id: 2L)을 수강 승인
        Student student = new Student(2L, 2L);

        assertThatThrownBy(() -> {
            instructor.approve(student);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("진행하는 강의의 수강 대기생이 아닙니다.");
    }

}
