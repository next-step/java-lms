package nextstep.courses.domain;

import nextstep.courses.type.ImageExtension;
import nextstep.courses.type.InfinitablePositiveInteger;
import nextstep.courses.type.SessionDuration;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.type.SessionState.*;
import static org.assertj.core.api.Assertions.*;

class SessionTest {
    private Session session;
    private Session paidSession;

    private Course sampleCourse;


    @BeforeEach
    public void sampleDataSetUp() {
        sampleCourse = new Course();

        SessionImage image = new SessionImage(100, 300, 200, ImageExtension.JPG);
        SessionDuration duration = SessionDuration.fromIso8601("2023-12-06T10:23:10.000", "2023-12-07T10:00:00.000");

        session = Session.createFreeSession(1L, sampleCourse, image, duration);
        paidSession = Session.createPaidSession(2L, sampleCourse, image, duration, InfinitablePositiveInteger.of(1), 100);
    }

    @Test
    @DisplayName("[Session.registerUser()] 같은 사용자가 이미 등록한 강의에 또 등록하면 -> 예외 던짐")
    public void duplicateRegisterTest() {
        assertThatThrownBy(() -> {
            session.registerUser(NsUserTest.SANJIGI);
            session.registerUser(NsUserTest.SANJIGI);
        })
                .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("[Session.registerUser()] 강의는 모집중 상태에서만 수강생 등록 가능")
    public void reigsterStateTest() {
        SessionImage image = new SessionImage(100, 300, 200, ImageExtension.JPG);
        SessionDuration duration = SessionDuration.fromIso8601("2023-12-06T10:23:10.000", "2023-12-07T10:00:00.000");
        Session readySession = Session.createFreeSession(1L, sampleCourse, image, duration);
        Session recruitSession = Session.createFreeSession(2L, sampleCourse, image, duration);
        Session endSession = Session.createFreeSession(3L, sampleCourse, image, duration);

        readySession.updateStateTo(READY);
        recruitSession.updateStateTo(RECRUIT);
        endSession.updateStateTo(END);

        assertThatCode(() -> {
            readySession.registerUser(NsUserTest.JAVAJIGI);
        }).doesNotThrowAnyException();

        assertThatThrownBy(() -> {
            recruitSession.registerUser(NsUserTest.JAVAJIGI);
        }).isInstanceOf(Exception.class);

        assertThatThrownBy(() -> {
            endSession.registerUser(NsUserTest.JAVAJIGI);
        }).isInstanceOf(Exception.class);
    }
    @Test
    @DisplayName("[paidSession.registerUser] 최대 수강 인원에 도달했는데 또 사용자 추가하면 -> 예외 던짐")
    public void registerOverflowTest() {
        Payment javajigiPayment = new Payment("0", paidSession, NsUserTest.JAVAJIGI, 100L);
        paidSession.registerUser(NsUserTest.JAVAJIGI, javajigiPayment);

        Payment sanjigiPayment = new Payment("1", paidSession, NsUserTest.SANJIGI, 100L);

        assertThatThrownBy(() -> {
            paidSession.registerUser(NsUserTest.SANJIGI, sanjigiPayment);
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("[PaidSession.registerUser] 강의료와 지불 금액이 같지 않은 상태로 수강 등록을 하면 -> 예외 던짐")
    public void amountNoMatchTest() {
        Payment wrongPayment = new Payment("0", paidSession, NsUserTest.JAVAJIGI, 80L);

        assertThatThrownBy(() -> {
            paidSession.registerUser(NsUserTest.JAVAJIGI, wrongPayment);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("[PaidSession.registerUser] 지불한 사람과 등록하는 사람이 같지 않으면 -> 예외 던짐")
    public void userNoMatchTest() {
        Payment wrongPayment = new Payment("0", paidSession, NsUserTest.JAVAJIGI, 100L);

        assertThatThrownBy(() -> {
            paidSession.registerUser(NsUserTest.SANJIGI, wrongPayment);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}