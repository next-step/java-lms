package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("수강자들 객체 테스트")
class StudentsTest {

    public static final LocalDateTime createDate = LocalDateTime.of(2023, 5, 27, 19, 30, 30);
    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net", createDate, null);
    public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", createDate, null);
    public static final NsUser WOOK = new NsUser(3L, "wook", "password", "name", "wook@gmail.com");

    @DisplayName("수강자들 객체 생성시 최대 수강 인원을 지정 할 수 있다")
    @Test
    void maxEnrollment() {
        Students students = new Students(50);
        assertThat(students.maxEnrollmentValue()).isEqualTo(50);
    }

    @DisplayName("수강자들 객체에 유저(학생)를 추가 할 수 있다")
    @Test
    void addUser() {
        Students students = new Students(3);
        students.addStudent(JAVAJIGI);
        students.addStudent(SANJIGI);

        Assertions.assertThat(students.countStudents()).isEqualTo(2);
        Assertions.assertThat(students.fetchStudents())
                .usingDefaultElementComparator()
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(
                        new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net", createDate, null),
                        new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", createDate, null)
                );
    }

    @DisplayName("최대 수강 인원을 초과하여 학생을 추가하면 예외가 발생한다")
    @Test
    void overCapacity() {
        Students students = new Students(2);

        students.addStudent(JAVAJIGI);
        students.addStudent(SANJIGI);

        assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        students.addStudent(WOOK))
                .withMessage("cannot enroll because the maximum capacity has been exceeded");
    }
}
