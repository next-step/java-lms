package nextstep.courses.domain.vo;

import nextstep.courses.exception.AlreadyEnrolledException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StudentsTest {

    public static Students STUDENTS = new Students(new ArrayList<>(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)));
    public static Students STUDENT_JAVAJIGI1 = new Students(new ArrayList<>(List.of(NsUserTest.JAVAJIGI)));
    public static Students STUDENT_JAVAJIGI2 = new Students(new ArrayList<>(List.of(NsUserTest.JAVAJIGI)));

    @Test
    @DisplayName("학생 수 테스트")
    void studentsCountTest() {
        assertThat(STUDENTS.count()).isEqualTo(2);
    }

    @Test
    @DisplayName("학생 추가 실패 테스트 - 학생 이미 존재")
    void studentsAddFailTest() {
        assertThatThrownBy(() ->
                STUDENTS.add(NsUserTest.SANJIGI))
                .isInstanceOf(AlreadyEnrolledException.class)
                .hasMessage("이미 수강신청한 학생입니다.");
    }
}
