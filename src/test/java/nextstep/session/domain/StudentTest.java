package nextstep.session.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentTest {

    @DisplayName("두 학생을 비교할 수 있다.")
    @Test
    void compareUser() {
        // given
        Student student1 = new Student(NsUserTest.JAVAJIGI);
        Student student2 = new Student(NsUserTest.JAVAJIGI);
        Student student3 = new Student(NsUserTest.SANJIGI);

        // then
        assertThat(student1.matchUser(student2))
                .isTrue();
        assertThat(student1.matchUser(student3))
                .isFalse();
    }

    @DisplayName("신청을 삭제 시 삭제 상태가 변한다.")
    @Test
    void changeDeleteStatusWhenUnapply() {
        // given
        Student student = new Student(NsUserTest.JAVAJIGI);

        // when
        student.delete(NsUserTest.SANJIGI);

        // then
        assertThat(student.toVO().isDeleted()).isTrue();
    }

    @DisplayName("승인된 상태에서는 승인할 수 없다.")
    @Test
    void cannotApproveWhenApproved() {
        // given
        Student student = new Student(NsUserTest.JAVAJIGI);

        // when
        student.approve();

        // then
        assertThatThrownBy(student::approve)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("취소된 상태에서는 거절할 수 없다.")
    @Test
    void cannotDenyWhenCanceled() {
        // given
        Student student = new Student(NsUserTest.JAVAJIGI);

        // when
        student.deny();

        // then
        assertThatThrownBy(student::deny)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
