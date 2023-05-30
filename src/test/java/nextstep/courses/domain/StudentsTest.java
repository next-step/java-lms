package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SampleUser.*;

@DisplayName("수강자들 객체 테스트")
class StudentsTest {

    @DisplayName("수강자들 객체에 유저(학생)를 추가 할 수 있다")
    @Test
    void addUser() {
        Students students = new Students();
        students.addStudent(JAVAJIGI);
        students.addStudent(SANJIGI);

        Assertions.assertThat(students.countEnrollment()).isEqualTo(2);
        Assertions.assertThat(students.fetchStudents())
                .usingDefaultElementComparator()
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(
                        new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net", createDate, null),
                        new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", createDate, null)
                );
    }
}
