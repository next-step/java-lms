package nextstep.courses.domian;

import nextstep.courses.domain.Students;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentsTest {

    @Test
    @DisplayName("처음 수강신청을 하는 학생이라면 정상적으로 수강신청이 가능하다.")
    void addStudent_success() {
        Students students = new Students(new ArrayList<>(List.of(NsUserTest.SANJIGI)));

        students.add(NsUserTest.JAVAJIGI);

        assertThat(students.totalCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("이미 수강신청을 완료한 학생이라면 중복 수강신청이 불가능하다.")
    void addStudent_already() {
        Students students = new Students(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI));

        assertThatThrownBy(() -> students.add(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 수강신청을 완료한 학생입니다.");
    }
}
