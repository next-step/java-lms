package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaidSessionTest {

    private List<NsUser> userList;
    private NsUser JAVAJIGI, SANJIGI;
    private Payment payment;


    @BeforeEach
    void beforeTest(){
        userList = new ArrayList<>();
        JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        payment = new Payment("test_id", 0L, 1L, 10000L);
        JAVAJIGI.addPayment(payment);

        SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
        userList.add(JAVAJIGI);
        userList.add(SANJIGI);

    }
    @Test
    void PaidSession_생성자_01() {
        new PaidSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.PREPARING, 100L, 10000L);
    }

    @Test
    void PaidSession_생성자_02() {
        new PaidSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.PREPARING, userList, 100L, 10000L);
    }

    @Test
    void 수강신청_정상() {
        PaidSession paidSession = new PaidSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.RECRUITING, 1L, 10000L);

        Assertions.assertThatCode(() -> {
            paidSession.join(JAVAJIGI);
        }).doesNotThrowAnyException();
    }

    @Test
    void 수강신청_예외_인원초과() {
        PaidSession paidSession = new PaidSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.RECRUITING, 0L, 10000L);

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.join(JAVAJIGI);
        });
    }

    @Test
    void 수강신청_예외_모집전() {
        PaidSession paidSession = new PaidSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.PREPARING, 1L, 10000L);

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.join(JAVAJIGI);
        });
    }

    @Test
    void 수강신청_예외_이미수강중() {
        PaidSession paidSession = new PaidSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.RECRUITING, 2L, 10000L);
        paidSession.join(JAVAJIGI);

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.join(JAVAJIGI);
        });
    }

    @Test
    void 수강신청_예외_결제금액미충족() {
        PaidSession paidSession = new PaidSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.RECRUITING, 2L, 100000L);

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.join(JAVAJIGI);
        });
    }
}
