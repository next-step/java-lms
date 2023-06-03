package nextstep.courses.domain.registration;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class StudentsTest {
    @DisplayName("강의는 강의 최대 수강 인원을 초과할 수 없다.")
    @Test
    void 수강신청_수강인원초과_불가능() {
        Students students = StudentsBuilder.aStudentsBuilder()
                .withMaxUserCount(1)
                .withUsers(NsUserTest.JAVAJIGI)
                .build();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> students.add(NsUserTest.SANJIGI))
                .withMessageMatching("최대 수강 인원을 초과했습니다.");

    }

    @DisplayName("중복 신청된 경우 예외처리")
    @Test
    void 수강신청_중복신청된_경우() {
        Students students = StudentsBuilder.aStudentsBuilder()
                .withUsers(NsUserTest.JAVAJIGI)
                .build();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> students.add(NsUserTest.JAVAJIGI))
                .withMessageMatching("이미 등록 되었습니다.");
    }
}
