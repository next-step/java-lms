package nextstep.sessions.domain;

import nextstep.courses.domain.CourseTest;
import nextstep.enrollment.domain.Enrollment;
import nextstep.images.domain.ImageTest;
import nextstep.sessions.domain.enums.ProgressStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SessionTest {
    public static final Session S1 = Session.ofDefaultCoverImage(1L, CourseTest.C, LocalDateTime.MIN, LocalDateTime.MAX, true, 10);
    public static final Session S2 = Session.ofDefaultCoverImage(2L, CourseTest.C, LocalDateTime.MIN, LocalDateTime.MAX, true, 10);
    private Session readySession;
    private Session openSession;
    private LocalDateTime now;

    @BeforeEach
    public void beforeEach() {
        readySession = Session.ofDefaultCoverImage(1L, CourseTest.C, LocalDateTime.MIN, LocalDateTime.MAX, true, 10);
        openSession = Session.of(1L, CourseTest.C, LocalDateTime.MIN, LocalDateTime.MAX, ImageTest.DEFAULT_IMAGE, true, ProgressStatus.OPEN, 10);
        now = LocalDateTime.now();
    }

    @DisplayName("Session 객체가 잘 생성되는지 확인")
    @Test
    void Session_객체가_정상적으로_생성되는지_확인() {
        assertThat(Session.ofDefaultCoverImage(1L, CourseTest.C, LocalDateTime.MIN, LocalDateTime.MAX, true, 10)).isInstanceOf(Session.class);
    }

    static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(null, LocalDateTime.now()),
                Arguments.of(LocalDateTime.now(), null),
                Arguments.of(null, null)
        );
    }

    @DisplayName("시작일 또는 종료일의 값이 유효하지 않은경우 IllegalArgumentException 발생하는지 확인")
    @ParameterizedTest(name = "from : {0}, to : {1}")
    @MethodSource("provideArguments")
    void 시작일_또는_종료일의_값이_유효하지_않은경우_IllegalArgumentException(LocalDateTime from, LocalDateTime to) {
        assertThatThrownBy(() -> Session.ofDefaultCoverImage(1L, CourseTest.C, from, to, true, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시작일 또는 종료일은 null 일 수 없습니다.");
    }

    @DisplayName("시작일이 종료일과 같거나 늦는 경우 IllegalArgumentException 발생하는지 확인")
    @Test
    void 시작일이_종료일과_같거나_늦는_경우_IllegalArgumentException() {
        LocalDateTime now = LocalDateTime.now();

        assertThatThrownBy(() -> Session.ofDefaultCoverImage(1L, CourseTest.C, now, now, true, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시작일이 종료일과 같거나 이후일 수 없습니다.");
    }

    @DisplayName("강의 생성 시 커버 이미지가 없는경우 default 커버 이미지로 적용되는지 확인")
    @Test
    void 강의_생성_시_커버_이미지가_없는경우_default_커버_이미지로_적용되는지_확인() {
        assertThat(readySession.getCoverImage()).isEqualTo(ImageTest.DEFAULT_IMAGE);
    }

    @DisplayName("무료 강의 유료 강의 객체가 정상적으로 생성되는지 확인")
    @Test
    void 무료_강의_유료_강의_객체가_정상적으로_생성되는지_확인() {
        Session freeSession = Session.ofFreeSession(1L, CourseTest.C, LocalDateTime.MIN, LocalDateTime.MAX, ImageTest.DEFAULT_IMAGE, 10);
        Session chargedSession = Session.ofChargedSession(2L, CourseTest.C, LocalDateTime.MIN, LocalDateTime.MAX, ImageTest.DEFAULT_IMAGE, 10);

        assertThat(freeSession.isFree()).isTrue();
        assertThat(chargedSession.isFree()).isFalse();
    }

    @DisplayName("강의상태가 모집중으로 변경되는지 확인")
    @Test
    void 강의상태가_모집중으로_변경되는지_확인() {
        readySession.toOpen();
        assertThat(readySession.getStatus()).isEqualTo(ProgressStatus.OPEN);
    }

    @DisplayName("강의상태가 종료로 변경되는지 확인")
    @Test
    void 강의상태가_종료로_변경되는지_확인() {
        readySession.toClose();
        assertThat(readySession.getStatus()).isEqualTo(ProgressStatus.CLOSED);
    }

    @DisplayName("수강신청시 모집중 이외의 상태인 경우 RuntimeException 발생하는지 확인")
    @ParameterizedTest
    @EnumSource(ProgressStatus.class)
    void 수강신청시_모집중_이외의_상태인_경우_RuntimeException_발생하는지_확인(ProgressStatus status) {
        if (ProgressStatus.OPEN == status) {
            return;
        }

        Session session = Session.of(1L, CourseTest.C, LocalDateTime.MIN, LocalDateTime.MAX, ImageTest.DEFAULT_IMAGE, true, status, 10);
        assertThatThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI, now))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("모집중 이외의 상태에서는 수강신청이 불가합니다.");
    }

    @DisplayName("수강신청시 모집중 상태인 경우 통과하는지 확인")
    @Test
    void 수강신청시_모집중_상태인_경우_통과하는지_확인() {
        readySession.toOpen();
        assertThatCode(() -> readySession.enroll(NsUserTest.JAVAJIGI, now))
                .doesNotThrowAnyException();
    }

    @DisplayName("최대 수강신청 인원이 1보다 작은 경우 IllegalArgumentException 발생하는지 확인")
    @Test
    void 최대_수강신청_인원이_1보다_작은_경우_IllegalArgumentException_발생하는지_확인() {
        assertThatThrownBy(() -> Session.ofDefaultCoverImage(1L, CourseTest.C, LocalDateTime.MIN, LocalDateTime.MAX, true, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최대 수강신청 인원 수가 1보다 작을 수 없습니다.");
    }

    @DisplayName("수강신청시 최대 수강신청 인원을 초과하는 경우 RuntimeException 발생하는지 확인")
    @Test
    void 수강신청시_최대_수강신청_인원을_초과하는_경우_RuntimeException_발생하는지_확인() {
        Session session = Session.of(1L, CourseTest.C, LocalDateTime.MIN, LocalDateTime.MAX, ImageTest.DEFAULT_IMAGE, true, ProgressStatus.OPEN, 1);
        session.enroll(NsUserTest.JAVAJIGI, now);

        assertThatThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI, now))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("최대 수강신청 인원을 초과하여 수강 신청을 할 수 없습니다.");
    }

    @DisplayName("수강신청시 수강신청인원이 1증가하는지 확인")
    @Test
    void 수강신청시_수강신청인원이_1증가하는지_확인() {
        int enrollmentCount = openSession.getEnrollmentCount();

        openSession.enroll(NsUserTest.JAVAJIGI, now);

        assertThat(openSession.getEnrollmentCount()).isEqualTo(enrollmentCount + 1);
    }

    @DisplayName("수강신청시 수강신청내역 객체가 반한되는지 확인")
    @Test
    void 수강신청시_수강신청내역_객체가_반한되는지_확인() {
        assertThat(openSession.enroll(NsUserTest.JAVAJIGI, now)).isEqualTo(Enrollment.of(openSession, NsUserTest.JAVAJIGI, now));
    }
}
