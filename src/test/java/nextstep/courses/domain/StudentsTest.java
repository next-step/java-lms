package nextstep.courses.domain;

import nextstep.courses.exception.AlreadyAddStudentException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentsTest {

    @Test
    @DisplayName("이미 신청한 학생은 신청 할 수 없다")
    void add() {
        NsUser user = new NsUser(1L, "테스트", "테스트", "테스트", "test@test.com");
        Students students = new Students(List.of(user));

        assertThrows(AlreadyAddStudentException.class, () -> students.canEnrol(user), "이미 수강을 신청한 학생입니다.");
    }
}
