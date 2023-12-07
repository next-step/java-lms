package nextstep.lms.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class StudentsTest {
    @DisplayName("유료강의 최대 수강 인원을 초과하지 않는다면 수강생 추가")
    @Test
    void 유료_수강생_추가() {
        Students students = new Students(Arrays.asList(NsUserTest.JAVAJIGI.getId()));
        int existingStudentsSize = students.size();
        students.paidSessionEnroll(Integer.MAX_VALUE, NsUserTest.SANJIGI.getId());
        assertThat(students.size()).isEqualTo(existingStudentsSize + 1);
    }

    @DisplayName("유료강의 최대 수강 인원을 초과한다면 예외 발생")
    @Test
    void 유료_최대_수강_인원_초과() {
        Students students = new Students(Arrays.asList(NsUserTest.JAVAJIGI.getId()));
        assertThatThrownBy(() -> students.paidSessionEnroll(1, NsUserTest.SANJIGI.getId()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 수강 인원을 초과할 수 없습니다.");
    }

    @DisplayName("유료강의 중복 수강 신청 시 예외발생")
    @Test
    void 유료_중복_수강_신청_불가() {
        Students students = new Students(Arrays.asList(NsUserTest.JAVAJIGI.getId()));
        assertThatThrownBy(() -> students.paidSessionEnroll(Integer.MAX_VALUE, NsUserTest.JAVAJIGI.getId()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 수강중인 강의입니다.");
    }

    @DisplayName("무료강의는 조건없이 수강신청 가능")
    @Test
    void 무료_수강생_추가() {
        Students students = new Students(Arrays.asList(NsUserTest.JAVAJIGI.getId()));
        int existingStudentsSize = students.size();
        students.freeSessionEnroll(NsUserTest.SANJIGI.getId());
        assertThat(students.size()).isEqualTo(existingStudentsSize + 1);
    }

    @DisplayName("무료강의 중복 수강 신청 시 예외발생")
    @Test
    void 무료_중복_수강_신청_불가() {
        Students students = new Students(Arrays.asList(NsUserTest.JAVAJIGI.getId()));
        assertThatThrownBy(() -> students.freeSessionEnroll(NsUserTest.JAVAJIGI.getId()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 수강중인 강의입니다.");
    }
}