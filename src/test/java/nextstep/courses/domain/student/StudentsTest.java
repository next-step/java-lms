package nextstep.courses.domain.student;

import nextstep.courses.exceptions.AlreadyEnrollmentException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static nextstep.users.domain.NsUserFixture.HYUNGKI;
import static nextstep.users.domain.NsUserFixture.JAVAJIGI;
import static nextstep.users.domain.NsUserFixture.SANJIGI;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class StudentsTest {

    private final ArrayList<NsUser> 기존_유저_목록 = new ArrayList<>(Arrays.asList(JAVAJIGI, SANJIGI));

    private final ArrayList<NsUser> 최대_유저_목록 = new ArrayList<>(Arrays.asList(JAVAJIGI, SANJIGI, HYUNGKI));

    private static final int 수강생_최대_인원 = 3;

    @Test
    @DisplayName("수강생 생성")
    void createStudentTest() {
        Students students = new Students(수강생_최대_인원, 기존_유저_목록);
        assertThat(students.sizeOfStudents()).isEqualTo(기존_유저_목록.size());
    }

    @Test
    @DisplayName("수강 신청 성공")
    void enrollTest() throws AlreadyEnrollmentException {
        Students students = new Students(수강생_최대_인원, 기존_유저_목록);
        students.enroll(HYUNGKI);

        assertThat(students.sizeOfStudents()).isEqualTo(수강생_최대_인원);
    }

    @Test
    @DisplayName("수강 신청 실패 - 이미 수강 신청한 학생")
    void enrollFailTest() {
        Students students = new Students(수강생_최대_인원, 기존_유저_목록);

        assertThatThrownBy(() -> students.enroll(JAVAJIGI))
                .isInstanceOf(AlreadyEnrollmentException.class);
    }

    @Test
    @DisplayName("수강 신청 실패 - 수강생 최대 인원 초과")
    void enrollFailTest2() {
        Students students = new Students(수강생_최대_인원, 최대_유저_목록);

        assertThatThrownBy(() -> students.enroll(HYUNGKI))
                .isInstanceOf(IllegalArgumentException.class);
    }

}