//package nextstep.courses.domain;
//
//import nextstep.courses.CannotRegisterException;
//import nextstep.users.domain.NsUser;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//public class SessionTest {
//
//    public static Session S1, S2, S3, S4;
//
//    @BeforeEach
//    void setup() {
//        S1 = new Session(1L, "TDD with JAVA 16", 16, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2),
//                SessionType.FREE, SessionStatus.PREPARING, 3);
//        S2 = new Session(2L, "TDD with Kotlin 5", 5, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1),
//                SessionType.PAID, SessionStatus.RECRUITING, 3);
//        S3 = new Session(3L, "DDD", 1, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1),
//                SessionType.PAID, SessionStatus.RECRUITING, 3);
//        S4 = new Session(1L, "TDD with JAVA 16", 16, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2),
//                SessionType.FREE, SessionStatus.RECRUITING, 3);
//
//        S2.register(new NsUser());
//        S2.register(new NsUser());
//        S2.register(new NsUser());
//        S3.register(new NsUser());
//        S3.register(new NsUser());
//    }
//
//    @Test
//    void create() {
//        assertThat(S1).isNotNull();
//    }
//
//    @Test
//    void 강의_모집기간이_아닙니다_예외처리() {
//        assertThatThrownBy(() -> S1.register(new NsUser()))
//                .isInstanceOf(CannotRegisterException.class)
//                .hasMessage("강의 모집기간이 아닙니다.");
//    }
//
//    @Test
//    void 강의_모집일_벗어났을시_예외처리() {
//        assertThatThrownBy(() -> S4.register(new NsUser()))
//                .isInstanceOf(CannotRegisterException.class)
//                .hasMessage("모집일이 아닙니다.");
//    }
//
//    @Test
//    void 등록_인원_정원_초과_예외처리() {
//        assertThatThrownBy(() -> S2.register(new NsUser()))
//                .isInstanceOf(CannotRegisterException.class)
//                .hasMessage("등록 인원이 정원 초과 되었습니다.");
//    }
//
//    @Test
//    void 수강_신청시_학생_추가() {
//        int beforeRegisterCount = S3.currRegisterCount();
//        S3.register(new NsUser());
//        int currRegisterCount = S3.currRegisterCount();
//
//        assertThat(beforeRegisterCount + 1).isEqualTo(currRegisterCount);
//    }
//
//}
