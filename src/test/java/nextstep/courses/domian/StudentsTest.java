package nextstep.courses.domian;

import nextstep.courses.domain.ApplyStatus;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentsTest {

    @Test
    @DisplayName("처음 수강신청을 하는 학생이라면 정상적으로 수강신청이 가능하다.")
    void addStudent_success() {
        Students students = new Students();

        students.add(NsUserTest.JAVAJIGI);

        assertThat(students.applyStudentsCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("이미 수강신청을 완료한 학생이라면 중복 수강신청이 불가능하다.")
    void addStudent_already() {
        Students students = new Students(List.of(new Student(NsUserTest.JAVAJIGI, ApplyStatus.APPLYING)));

        assertThatThrownBy(() -> students.add(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 수강신청을 완료한 학생입니다.");
    }

    @Test
    @DisplayName("수강신청한 학생의 수강신청을 승인하면 상태가 승인 상태가 된다.")
    void approve_student() {
        Students students = new Students(List.of(new Student(NsUserTest.JAVAJIGI, ApplyStatus.APPLYING)));

        students.approve(new Student(NsUserTest.JAVAJIGI));

        int actual = students.approvalStudentsCount();

        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("존재하지 않는 학생의 수강신청을 승인할 경우 오류가 발생한다.")
    void approve_student_fail() {
        Students students = new Students(List.of(new Student(NsUserTest.JAVAJIGI)));

        assertThatThrownBy(() -> students.approve(new Student(NsUserTest.SANJIGI)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당하는 학생이 없습니다.");
    }
}
