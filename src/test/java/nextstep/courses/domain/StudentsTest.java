package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class StudentsTest {
    @Test
    @DisplayName("강의 최대 수강 인원을 초과하면 예외가 던져진다")
    void charge_session_capacity_exception() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        NsUser sanjigi = NsUserTest.SANJIGI;

        Students students = new Students();
        students.add(javajigi, 1);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            students.add(sanjigi, 1);
        });
    }

    @Test
    @DisplayName("수강생이 똑같은 강의를 수강신청하면 예외가 던져진다")
    void duplicate_exception() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        Students students = new Students();
        students.add(javajigi, 1);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            students.add(javajigi, 1);
        });
    }
}
