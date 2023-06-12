package nextstep.courses.domain.session;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        Students students = new Students(
                1,
                SessionFeeType.FREE,
                SessionStatus.PREPARING,
                SessionRecruitment.CLOSE,
                List.of(new Student(0L, NsUserTest.JAVAJIGI.getId())));
        assertThatThrownBy(() -> students.validateRegister(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("수강 신청은 수강 모집 최대 인원을 초과할 수 없다.")
    @Test
    public void registerTest_shouldNotExceedCapacity() {
        Students students = new Students(
                1,
                SessionFeeType.FREE,
                SessionStatus.PREPARING,
                SessionRecruitment.OPEN,
                List.of(new Student(0L, NsUserTest.JAVAJIGI.getId())));
        assertThatThrownBy(() -> students.validateRegister(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("중복 수강 신청은 불가능하다.")
    @Test
    public void registerTest_shouldNotRegisterMoreThanTwice() {
        Students students = new Students(
                1,
                SessionFeeType.FREE,
                SessionStatus.PREPARING,
                SessionRecruitment.OPEN,
                List.of(new Student(0L, NsUserTest.JAVAJIGI.getId())));
        assertThatThrownBy(() -> students.validateRegister(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("수강 신청하지 않은 사용자는 수강 승인이 불가능하다.")
    @Test
    public void approveTest_수강신청하지_않은_학생() {
        Students students = new Students(
                1,
                SessionFeeType.FREE,
                SessionStatus.PREPARING,
                SessionRecruitment.OPEN,
                List.of(new Student(0L, NsUserTest.JAVAJIGI.getId())));
        assertThatThrownBy(() -> students.validateExist(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
