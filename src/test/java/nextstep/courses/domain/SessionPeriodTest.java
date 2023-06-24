package nextstep.courses.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("강의 기간 기능 테스트")
class SessionPeriodTest {

    @Nested
    @DisplayName("강의 기간 객체 생성 테스트")
    public class 강의_기간_객체_생성_테스트 {

        @Test
        @DisplayName("강의 시작일이 입력되지 않으면 예외를 던진다")
        void 강의_시작일이_입력되지_않으면_예외를_던진다() {
            Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new SessionPeriod(2L, null, LocalDate.now()));
            assertEquals("강의 시작일이 등록되질 않았어요 :(", exception.getMessage());
        }

        @Test
        @DisplayName("강의 종료일이 입력되지 않으면 예외를 던진다")
        void 강의_종료일이_입력되지_않으면_예외를_던진다() {
            Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new SessionPeriod(2L, LocalDate.now(), null));
            assertEquals("강의 종료일이 등록되질 않았어요 :(", exception.getMessage());
        }

        @Test
        @DisplayName("강의 기간 객체 생성 확인")
        void 객체_생성테스트() {

            SessionPeriod sessionPeriod = new SessionPeriod(2L, LocalDate.now(), LocalDate.now());
            assertAll(
                    () -> assertThat(sessionPeriod).isNotNull(),
                    () -> assertThat(sessionPeriod).isInstanceOf(SessionPeriod.class)
            );
        }
    }

    @Nested
    @DisplayName("강의 시작 시간 변경 테스트")
    public class 강의_시작_시간_변경_테스트 {

        private final SessionPeriod sessionPeriod = new SessionPeriod(2L, LocalDate.now(), LocalDate.now().plusDays(2));

        @Test
        @DisplayName("강의시작 날짜가 현재 날짜보다 앞일 경우 예외를 던진다")
        void 강의시작_날짜가_현재_날짜보다_앞일_경우_예외를_던진다() {


            Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sessionPeriod.changeStartDate(2L, LocalDate.now().minusDays(1)));
            assertEquals("강의 시작 날짜가 현재 날짜보다 앞일 수 없습니다 :(", exception.getMessage());
        }

        @Test
        @DisplayName("강의 시작 날짜가 강의 종료 날짜가 보다 앞일 경우 예외를 던진다")
        void 강의시작_날짜가_강의_종료_날짜가_보다_앞일_경우_예외를_던진다() {


            Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sessionPeriod.changeStartDate(2L, LocalDate.now().plusDays(3)));
            assertEquals("강의 시작 날짜가 강의 종료 날짜가 보다 앞일 수 없습니다 :(", exception.getMessage());
        }

        @Test
        @DisplayName("강의 시작 시간 변경 확인")
        void 강의_시작_시간_변경_성공() {

            SessionPeriod actualSessionPeriod = sessionPeriod.changeStartDate(2L, LocalDate.now().plusDays(1));
            SessionPeriod expectedSessionPeriod = new SessionPeriod(2L, LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));

            assertThat(actualSessionPeriod).isEqualTo(expectedSessionPeriod);
        }
    }

    @Nested
    @DisplayName("강의 종료 시간 변경 테스트")
    public class 강의_종료_시간_변경_테스트 {

        private final SessionPeriod sessionPeriod = new SessionPeriod(2L, LocalDate.now(), LocalDate.now().plusDays(2));

        @Test
        @DisplayName("강의종료 날짜가 현재 날짜보다 앞일 경우 예외를 던진다")
        void 강의종료_날짜가_현재_날짜보다_앞일_경우_예외를_던진다() {


            Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sessionPeriod.changeEndDate(2L, LocalDate.now().minusDays(1)));
            assertEquals("강의 종료 날짜가 강의 시작 날짜가 보다 앞일 수 없습니다 :(", exception.getMessage());
        }

        @Test
        @DisplayName("강의 종료 시간 변경 확인")
        void 강의_종료_시간_변경_성공() {

            SessionPeriod actualSessionPeriod = sessionPeriod.changeEndDate(2L, LocalDate.now().plusDays(2));
            SessionPeriod expectedSessionPeriod = new SessionPeriod(2L, LocalDate.now(), LocalDate.now().plusDays(2));

            assertThat(actualSessionPeriod).isEqualTo(expectedSessionPeriod);
        }
    }

    @Test
    @DisplayName("시작일로_부터의_차이_테스트")
    void 시작일로_부터의_차이_테스트() {
        LocalDate nowD1 = LocalDate.now().minusDays(1);
        SessionPeriod sessionPeriod = new SessionPeriod(2L, LocalDate.now(), LocalDate.now().plusDays(2));

        assertThat(sessionPeriod.calcDiffStartDateAnd(nowD1)).isEqualTo(1);
    }

}