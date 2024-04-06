package nextstep.session.domain;

import nextstep.courses.domain.Course;
import nextstep.exception.SessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FreeSessionTest {

    private Session session;

    @BeforeEach
    void setUp() {
        Resolution resolution = new Resolution(300, 200);
        ImageFilePath imageFilePath = new ImageFilePath("/home", "mapa", "jpg");
        Course course = new Course("Course1", 1L, 3);

        session = new FreeSession(
                new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)),
                new Cover(resolution, imageFilePath, 10000),
                "얼른 배우자 객체지향",
                course,
                1L,
                new Tutor(NsUserTest.JAVAJIGI)
        );
    }

    @DisplayName("상태가 ON_ENROLL이면서, 신청일자가 Duration에 속한다면, 신청 가능하다.")
    @Test
    void enrollAvailable() {
        // when
        session.toNextSessionStatus();

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(1)))
                .isTrue();
    }

    @DisplayName("상태가 ON_ENROLL이면서 신청일자가 Duration이 아니면, 신청이 불가능하다.")
    @Test
    void enrollNotAvailableWithNotInDuration() {
        // when
        session.toNextSessionStatus();

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(8)))
                .isFalse();
    }

    @DisplayName("상태가 ON_ENROLL이 아니고 신청일자가 Duration이 아니면, 신청이 불가능하다.")
    @Test
    void enrollNotAvailableWithNotOnEnroll() {
        // when
        session.toNextSessionStatus();
        session.toNextSessionStatus();

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(2)))
                .isFalse();
    }

    @DisplayName("수강신청 기간이면서 모집중이라면, 수강신청이 가능하다.")
    @Test
    void erollAvailable() {
        // given
        Payment payment = new Payment();

        // when
        session.toNextSessionStatus();

        // then
        assertThat(session.apply(NsUserTest.JAVAJIGI, payment, LocalDateTime.now().plusDays(2)))
                .isTrue();
    }

    @DisplayName("모집중이여도 수강신청 기간이 아니면, 수강신청이 불가능하다.")
    @Test
    void onEnrollNotInDurationIsUnavailable() {
        // given
        Payment payment = new Payment();

        // when
        session.toNextSessionStatus();

        // then
        assertThat(session.apply(NsUserTest.JAVAJIGI, payment, LocalDateTime.now()))
                .isFalse();
    }

    @DisplayName("수강신청 기간이여도 모집중이 아니라면, 수강신청이 불가능하다.")
    @Test
    void inDurationAndNotOnEnrollIsUnavailable() {
        // given
        Payment payment = new Payment();

        // then
        assertThat(session.apply(NsUserTest.JAVAJIGI, payment, LocalDateTime.now().plusDays(2)))
                .isFalse();
    }

    @DisplayName("준비중 상태가 아니면, 시작 일자는 변경할 수 없다.")
    @Test
    void cannotChangeStartDateIfNotOnReady() {
        // given
        session.toNextSessionStatus();

        // then
        assertThatThrownBy(() -> session.changeStartDate(LocalDateTime.now().plusDays(3)))
                .isInstanceOf(SessionException.class);
    }

    @DisplayName("준비중 상태가 아니면, 종료 일자는 변경할 수 없다.")
    @Test
    void cannotChangeEndDateIfNotOnReady() {
        // given
        session.toNextSessionStatus();

        // then
        assertThatThrownBy(() -> session.changeEndDate(LocalDateTime.now().plusDays(3)))
                .isInstanceOf(SessionException.class);
    }

    @DisplayName("준비중 상태가 아니면, 커버는 변경할 수 없다.")
    @Test
    void cannotChangeCoverIfNotOnReady() {
        // given
        session.toNextSessionStatus();
        Cover newCover = new Cover(
                new Resolution(600, 400),
                new ImageFilePath("C:/", "image", "jpg"),
                1_000_000L
        );

        // then
        assertThatThrownBy(() -> session.changeCover(newCover))
                .isInstanceOf(SessionException.class);
    }

    @DisplayName("준비중 상태가 아니면, 세션 이름은 변경할 수 없다.")
    @Test
    void cannotChangeSessionNameDateIfNotOnReady() {
        // given
        session.toNextSessionStatus();

        // then
        assertThatThrownBy(() -> session.editSessionName("새로운 세션 이름"))
                .isInstanceOf(SessionException.class);
    }
}
