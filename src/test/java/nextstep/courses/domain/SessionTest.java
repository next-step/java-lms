package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SessionTest {

    @Test
    @DisplayName("오늘 날짜가 강의 시작일 이전이 아닌데 준비중 상태를 가지면 예외를 발생한다.")
    void 오늘_날짜가_강의_시작일_이전이_아니면_준비중_상태_예외_발생() {
        assertThatThrownBy(() -> new Session(0L, SessionState.PREPARING, null, LocalDate.now().minusDays(1), LocalDate.now().plusDays(5)))
                .isInstanceOf(SessionException.class);
    }

    @Test
    @DisplayName("오늘 날짜가 강의 시작일 이전이 아닌데 모집중 상태를 가지면 예외를 발생한다.")
    void 오늘_날짜가_강의_시작일_이전이_아니면_모집중_상태_예외_발생() {
        assertThatThrownBy(() -> new Session(0L, SessionState.RECRUITING, null, LocalDate.now().minusDays(1), LocalDate.now().plusDays(5)))
                .isInstanceOf(SessionException.class);
    }

    @Test
    @DisplayName("오늘 날짜가 강의 종료일 이후가 아닌데 종료 상태를 가지면 예외를 발생한다.")
    void 오늘_날짜가_강의_종료일_이후가_아니면_종료_상태_예외_발생() {
        assertThatThrownBy(() -> new Session(0L, SessionState.END, null, LocalDate.now().minusDays(10), LocalDate.now().plusDays(5)))
                .isInstanceOf(SessionException.class);
    }

    @Test
    @DisplayName("모집중인 무료 강의는 최대 수강 인원 제한 없이 수강 신청을 할 수 있다.")
    void 무료강의_수강신청() {
        Session session = new Session(0L, SessionType.FREE, SessionState.RECRUITING,null, new ArrayList<>(), 15);
        session.enrollStudent(NsUserTest.JAVAJIGI);

        assertThat(session.equals(new Session(0L, SessionType.FREE, SessionState.RECRUITING,10, List.of(NsUserTest.JAVAJIGI), 16))).isTrue();
    }

    @Test
    @DisplayName("모집중인 유료강의에 수강신청 시 최대 수강 인원을 초과하면 예외가 발생한다.")
    void 유료강의_수강신청() {
        Session session = new Session(0L, SessionType.PAID, SessionState.RECRUITING,10, new ArrayList<>(), 10);

        assertThatThrownBy(() -> session.enrollStudent(NsUserTest.JAVAJIGI))
                .isInstanceOf(SessionException.class);
    }

    @Test
    @DisplayName("강의 상태가 모집중일 때 수강신청을 할 수 있다.")
    void 수강신청_모집중_상태() {
        Session session = new Session(0L, SessionState.RECRUITING, null, LocalDate.now().plusDays(3), LocalDate.now().plusDays(15));
        session.enrollStudent(NsUserTest.JAVAJIGI);

        assertThat(session.getEnrollCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("강의 상태가 준비중일 때 수강신청을 하면 예외가 발생한다.")
    void 수강신청_준비중_상태() {
        Session session = new Session(0L, SessionState.PREPARING, null, LocalDate.now().plusDays(3), LocalDate.now().plusDays(15));

        assertThatThrownBy(() -> session.enrollStudent(NsUserTest.JAVAJIGI))
                .isInstanceOf(SessionException.class);
    }

    @Test
    @DisplayName("강의 상태가 종료일 때 수강신청을 하면 예외가 발생한다.")
    void 수강신청_종료_상태() {
        Session session = new Session(0L, SessionState.END, null, LocalDate.now().minusDays(10), LocalDate.now().minusDays(3));

        assertThatThrownBy(() -> session.enrollStudent(NsUserTest.JAVAJIGI))
                .isInstanceOf(SessionException.class);
    }

    @Test
    @DisplayName("강의 생성시 이미지 형식 제한에 맞는 강의 커버 이미지 정보를 가진다.")
    void 이미지_형식_제한에_맞는_강의_커버_이미지() {
        ImageInfo imageInfo = new ImageInfo("JPG", 0.5, 300, 200);
        assertThatCode(() -> new Session(0L, SessionState.PREPARING, imageInfo, LocalDate.now().plusDays(3), LocalDate.now().plusDays(15)))
                .doesNotThrowAnyException();
    }
}