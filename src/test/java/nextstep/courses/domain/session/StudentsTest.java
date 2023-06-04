package nextstep.courses.domain.session;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentsTest {
    @DisplayName("수강 최대 인원은 0보다 커야 한다.")
    @Test
    public void createStudentsTest() {
        assertThatThrownBy(
                () -> new Students(0, SessionFeeType.FREE, SessionStatus.PREPARING))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수강 신청은 강의 상태가 모집 중일 때만 가능하다.")
    @Test
    public void registerTest_notRecruiting() {
        assertThatThrownBy(
                () ->
                        new Students(1, SessionFeeType.FREE, SessionStatus.PREPARING)
                                .addStudent(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("수강 신청은 수강 모집 최대 인원을 초과할 수 없다.")
    @Test
    public void registerTest_shouldNotExceedCapacity() {
        Students students = new Students(1, SessionFeeType.FREE, SessionStatus.RECRUITING);
        students.addStudent(NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> students.addStudent(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("중복 수강 신청은 불가능하다.")
    @Test
    public void registerTest_shouldNotRegisterMoreThanTwice() {
        Students students = new Students(1, SessionFeeType.FREE, SessionStatus.RECRUITING);
        students.addStudent(NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> students.addStudent(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class);
    }
}
