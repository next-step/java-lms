package nextstep.lms.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StudentTest {

    @Test
    @DisplayName("학생 생성 테스트")
    void createStudentTest() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        Session classOne = SessionTest.CLASS_ONE;

        Student student = Student.init(javajigi, classOne);

        assertThat(student.getStudentRegisterType())
                .isEqualTo(StudentRegisterType.REGISTERED.toString());
    }

    @Test
    @DisplayName("학생 취소 테스트")
    void cancelTest() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        Session classOne = SessionTest.CLASS_ONE;

        Student student = Student.init(javajigi, classOne);
        student.sessionCancel();

        assertThat(student.getStudentRegisterType())
                .isEqualTo(StudentRegisterType.CANCELED.toString());
    }

    @Test
    @DisplayName("학생 선발 테스트")
    void selectStudentTest() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        Session classOne = SessionTest.CLASS_ONE;

        Student student = Student.init(javajigi, classOne);

        assertThat(student.changeStudentSelect())
                .isEqualTo(StudentSelectedType.SELECTED);
    }

    @Test
    @DisplayName("선발된 학생 승인 변경 테스트")
    void changeSelectedStudentApprovedTest() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        Session classOne = SessionTest.CLASS_ONE;

        Student student = Student.init(javajigi, classOne);
        student.changeStudentSelect();

        assertThat(student.changeApprovedStatus())
                .isEqualTo(StudentApprovedType.APPROVED);
    }

    @Test
    @DisplayName("선발되지 않은 학생 승인 변경 에러 테스트")
    void changeNonSelectedStudentApprovedErrorTest() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        Session classOne = SessionTest.CLASS_ONE;

        Student student = Student.init(javajigi, classOne);

        assertThatThrownBy(student::changeApprovedStatus)
                .isInstanceOf(IllegalArgumentException.class);
    }

}
