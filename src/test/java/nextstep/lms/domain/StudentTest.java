package nextstep.lms.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {

    @Test
    @DisplayName("학생 생성 테스트")
    void createStudentTest() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        Session classOne = SessionTest.CLASS_ONE;

        Student student = Student.init(javajigi, classOne);

        assertThat(student.getRegisterType())
                .isEqualTo(RegisterType.REGISTERED.toString());
    }

    @Test
    @DisplayName("학생 취소 테스트")
    void cancelTest() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        Session classOne = SessionTest.CLASS_ONE;

        Student student = Student.init(javajigi, classOne);
        student.sessionCancel();

        assertThat(student.getRegisterType())
                .isEqualTo(RegisterType.CANCELED.toString());
    }

}
