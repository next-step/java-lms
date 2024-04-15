package nextstep.session.domain;

import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.courses.domain.Course;
import nextstep.exception.StudentsException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaidSessionTest {

    private Session session;

    @BeforeEach
    void setUp() {
        Duration duration = new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        Resolution resolution = new Resolution(300, 200);
        ImageFilePath imageFilePath = new ImageFilePath("/home", "mapa", "jpg");
        Cover cover = new Cover(1L, resolution, imageFilePath, 10000, NsUserTest.JAVAJIGI.getUserId());
        Course course = new Course("Course1", 1L, 3);

        session = new PaidSession(
                1L,
                duration,
                cover,
                "얼른 배우자 객체지향",
                course.getId(),
                2,
                100_000L,
                new Tutor(NsUserTest.JAVAJIGI)
        );
    }

    @DisplayName("모집중이면서, 신청일자가 Duration에 속하고, 신청인원이 꽉 차지 않았다면 신청 가능하다.")
    @Test
    void enrollAvailable() {
        // when
        session.toNextSessionStatus();
        session.changeEnroll();

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(1)))
                .isTrue();
    }

    @DisplayName("다른 조건이 만족하지만 상태가 모집중이 아니라면, 신청이 불가능하다.")
    @Test
    void cannotEnrollWithoutStatusCondition() {
        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(1)))
                .isFalse();
    }

    @DisplayName("다른 조건이 만족하지만 신청일자가 가능일자와 다르다면, 신청이 불가능하다.")
    @Test
    void cannotEnrollWithoutDurationCondition() {
        // when
        session.toNextSessionStatus();

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(8)))
                .isFalse();
    }

    @DisplayName("다른 조건이 만족하지만 수강인원이 모두 찼다면, 신청이 불가능하다.")
    @Test
    void cannotEnrollWithoutCountOfStudentsCondition() {
        // given
        Payment payment = new Payment("NORMAL", 1L, 1L, 100_000L);
        Student student1 = new Student(NsUserTest.SANJIGI);
        Student student2 = new Student(NsUserTest.JAVAJIGI);

        // when
        session.toNextSessionStatus();
        session.apply(student1, payment, LocalDateTime.now().plusDays(1));
        session.apply(student2, payment, LocalDateTime.now().plusDays(1));

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(1)))
                .isFalse();
    }

    @DisplayName("모든 조건이 일치하지 않는경우 신청이 불가능하다.")
    @Test
    void enrollNotAvailableWithNotOnEnroll() {
        // given
        Payment payment = new Payment("NORMAL", 1L, 1L, 100_000L);
        Student student1 = new Student(NsUserTest.SANJIGI);
        Student student2 = new Student(NsUserTest.SANJIGI);

        // when
        session.toNextSessionStatus();
        session.toNextSessionStatus();
        session.apply(student1, payment, LocalDateTime.now().plusDays(1));
        session.apply(student2, payment, LocalDateTime.now().plusDays(1));

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(8)))
                .isFalse();
    }

    @DisplayName("동일한 수강 신청은 불가능하다.")
    @Test
    void cannotApplySameStudentTwice() {
        // given
        Payment payment = new Payment("NORMAL", 1L, 1L, 100_000L);
        Student student = new Student(NsUserTest.SANJIGI);

        // when
        session.toNextSessionStatus();
        session.changeEnroll();
        session.apply(student, payment, LocalDateTime.now().plusDays(1));

        // then
        assertThatThrownBy(() -> session.apply(student, payment, LocalDateTime.now().plusDays(1)))
                .isInstanceOf(StudentsException.class);
    }

    @DisplayName("수강신청 기간이면서 모집중이라면, 수강신청이 가능하다.")
    @Test
    void erollAvailable() {
        // given
        Payment payment = new Payment("NORMAL", 1L, 1L, 100_000L);
        Student student = new Student(NsUserTest.SANJIGI);

        // when
        session.toNextSessionStatus();
        session.changeEnroll();

        // then
        assertThat(session.apply(student, payment, LocalDateTime.now().plusDays(2)))
                .isTrue();
    }

    @DisplayName("모집중이여도 수강신청 기간이 아니면, 수강신청이 불가능하다.")
    @Test
    void onEnrollNotInDurationIsUnavailable() {
        // given
        Payment payment = new Payment("NORMAL", 1L, 1L, 100_000L);
        Student student = new Student(NsUserTest.SANJIGI);

        // when
        session.toNextSessionStatus();

        // then
        assertThat(session.apply(student, payment, LocalDateTime.now()))
                .isFalse();
    }

    @DisplayName("세션을 삭제하면, 속한 커버와 학생들도 삭제된다.")
    @Test
    void delete() {
        // given
        session.toNextSessionStatus();
        session.changeEnroll();
        session.apply(new Student(NsUserTest.JAVAJIGI), new Payment("A", 1L, 1L, 100_000L), LocalDateTime.now().plusDays(2));

        // when
        session.toPreviousSessionStatus();
        DeleteHistoryTargets deleteHistoryTargets = session.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(deleteHistoryTargets.asList()).hasSize(3);
    }

    @DisplayName("존재하는 학생에 대해 승인할 수 있다.")
    @Test
    void canApproveExist() {
        // given
        Student student = new Student(NsUserTest.JAVAJIGI);
        session.changeEnroll();
        session.apply(student, new Payment("A", 1L, 1L, 100_000L), LocalDateTime.now().plusDays(2));

        // when
        session.approveStudent(student);

        // then
        assertThat(session.getStudents().findStudent(student).get().toVO().getApproved())
                .isEqualTo("APPROVED");
    }

    @DisplayName("존재하지 않는 학생에 대해 승인할 수 없다.")
    @Test
    void cannotApproveNotEixst() {
        // given
        Student student = new Student(NsUserTest.JAVAJIGI);
        session.changeEnroll();

        // then
        assertThatThrownBy(() -> session.approveStudent(student))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("존재하는 학생에 대해 거절할 수 있다.")
    @Test
    void canDenyExist() {
        // given
        Student student = new Student(NsUserTest.JAVAJIGI);
        session.changeEnroll();
        session.apply(student, new Payment("A", 1L, 1L, 100_000L), LocalDateTime.now().plusDays(2));

        // when
        session.denyStudent(student);

        // then
        assertThat(session.getStudents().findStudent(student).get().toVO().getApproved())
                .isEqualTo("CANCELED");
    }

    @DisplayName("존재하지 않는 학생에 대해 거절할 수 없다.")
    @Test
    void cannotDenyNotExist() {
        // given
        Student student = new Student(NsUserTest.JAVAJIGI);
        session.changeEnroll();

        // then
        assertThatThrownBy(() -> session.denyStudent(student))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
