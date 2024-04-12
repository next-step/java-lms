package nextstep.users.domain;

import nextstep.courses.domain.ChargedSession;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionStatus;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

public class NsUserTest {
    public static NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    public static SessionImage IMAGE = new SessionImage(1L, 300, 200, "jpg", 1024, "TEST_IMAGE", 1L);

    @BeforeEach
    void beforeEach() {
        JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
        IMAGE = new SessionImage(1L, 300, 200, "jpg", 1024, "TEST_IMAGE", 1L);
    }

    @Test
    void 결제이력_추가() {
        Payment testPayment = new Payment("TEST_PAYMENT", 1L, 1L, 10000L);
        JAVAJIGI.addPayment(testPayment);
        assertThat(JAVAJIGI.hasPayment(testPayment)).isTrue();
    }
    
    @ParameterizedTest
    @CsvSource(value = { "TEST_PAYMENT, true", "TEST_PAYMENT2, false" })
    void 결제이력_유무_확인(String input, Boolean result) {
        Payment testPayment = new Payment("TEST_PAYMENT", 1L, 1L, 10000L);
        JAVAJIGI.addPayment(testPayment);
        assertThat(JAVAJIGI.hasPayment(new Payment(input, 1L, 1L, 10000L))).isEqualTo(result);
    }
    
    @ParameterizedTest
    @CsvSource(value = { "1, false", "2, true" })
    void 유료강의에_대한_결제이력_확인(Long input, Boolean result) {
        ChargedSession session = new ChargedSession(input, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L), List.of(IMAGE), SessionStatus.CLOSED, 5, 10000L);
        Payment testPayment = new Payment("TEST_PAYMENT", 1L, 1L, 10000L);
        JAVAJIGI.addPayment(testPayment);

        assertThat(JAVAJIGI.notHasPaymentFor(session)).isEqualTo(result);
    }

    @Test
    void 유료_수강_신청() {
        ChargedSession session = new ChargedSession(1L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L), List.of(IMAGE), SessionStatus.OPEN, 5, 10000L);
        Payment testPayment = new Payment("TEST_PAYMENT", 1L, 1L, 10000L);
        JAVAJIGI.addPayment(testPayment);
        JAVAJIGI.register(session);

        assertThat(session.hasStudent(JAVAJIGI)).isTrue();
    }

    @Test
    void 무료_수강_신청() {
        FreeSession session = new FreeSession(1L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L), List.of(IMAGE), SessionStatus.OPEN);
        JAVAJIGI.register(session);

        assertThat(session.hasStudent(JAVAJIGI)).isTrue();
    }

    @Test
    void 정보_변경() {
        JAVAJIGI.update(JAVAJIGI, SANJIGI);
        assertThat(JAVAJIGI.equalsNameAndEmail(SANJIGI)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideMatchUserTestParameters")
    void 유저_일치_여부(NsUser input, boolean result) {
        assertThat(JAVAJIGI.matchUser(input)).isEqualTo(result);
    }

    private static List<Arguments> provideMatchUserTestParameters() {
        return List.of(
                arguments(JAVAJIGI, true),
                arguments(SANJIGI, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideMatchPasswordTestParameters")
    void 비밀번호_일치_여부(String input, boolean result) {
        assertThat(JAVAJIGI.matchPassword(input)).isEqualTo(result);
    }

    private static List<Arguments> provideMatchPasswordTestParameters() {
        return List.of(
                arguments("password", true),
                arguments("test", false)
        );
    }
}
