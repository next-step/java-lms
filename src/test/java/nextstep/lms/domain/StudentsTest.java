package nextstep.lms.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class StudentsTest {
    @DisplayName("수강생 추가 가능")
    @Test
    void 수강생_추가() {
        Students students = new Students(Arrays.asList(NsUserTest.JAVAJIGI.getId()));
        int existingStudentsSize = students.size();
        students.enroll(NsUserTest.SANJIGI.getId());
        assertThat(students.size()).isEqualTo(existingStudentsSize + 1);
    }

    @DisplayName("중복 수강신청시 예외 발생")
    @Test
    void 중복_수강신청() {
        Students students = new Students(Arrays.asList(NsUserTest.JAVAJIGI.getId()));

        assertThatThrownBy(() -> students.enroll(NsUserTest.JAVAJIGI.getId()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 수강중인 강의입니다.");
    }

    @DisplayName("유료강의 최대 수강 인원을 초과한다면 예외 발생")
    @Test
    void 최대_수강_인원_초과() {
        Students students = new Students(Arrays.asList(NsUserTest.JAVAJIGI.getId()));
        assertThatThrownBy(() -> students.capacityCheck(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 수강 인원을 초과할 수 없습니다.");
    }
}