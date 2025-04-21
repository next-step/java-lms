package nextstep.courses.domain.session;

import nextstep.courses.domain.session.enrollment.FreeEnrollment;
import nextstep.courses.domain.session.enrollment.PaidEnrollment;
import nextstep.courses.domain.session.info.SessionInfo;
import nextstep.courses.domain.session.info.basic.SessionBasicInfo;
import nextstep.courses.domain.session.info.basic.SessionThumbnail;
import nextstep.courses.domain.session.info.detail.SessionDetailInfo;
import nextstep.courses.domain.session.info.detail.SessionPeriod;
import nextstep.courses.domain.session.info.detail.SessionPrice;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    private static final NsUser USER = new NsUser(1L, "user", "password", "name", "email");
    private static final LocalDate START_DATE = LocalDate.now();
    private static final LocalDate END_DATE = START_DATE.plusMonths(1);
    public static final SessionThumbnail THUMBNAIL = new SessionThumbnail("image.jpg", 1024, 300, 200);

    @Test
    @DisplayName("강의를 생성한다")
    void create() {
        SessionPeriod period = new SessionPeriod(START_DATE, END_DATE);
        SessionPrice price = new SessionPrice(SessionType.PAID, 10000);
        SessionDetailInfo detailInfo = new SessionDetailInfo(period, price);
        SessionBasicInfo basicInfo = new SessionBasicInfo("강의 제목", THUMBNAIL);
        SessionInfo sessionInfo = new SessionInfo(basicInfo, detailInfo);
        Session session = new Session(
                new SessionId(1L, 1L),
                sessionInfo,
                new FreeEnrollment()
        );

        assertThat(session.isPaid()).isTrue();
    }

    @Test
    @DisplayName("무료 강의를 생성한다")
    void createFreeSession() {
        SessionPeriod period = new SessionPeriod(START_DATE, END_DATE);
        SessionPrice price = new SessionPrice(SessionType.FREE, 0);
        SessionDetailInfo detailInfo = new SessionDetailInfo(period, price);
        SessionBasicInfo basicInfo = new SessionBasicInfo("강의 제목", THUMBNAIL);
        SessionInfo sessionInfo = new SessionInfo(basicInfo, detailInfo);
        Session session = new Session(
                new SessionId(1L, 1L),
                sessionInfo,
                new FreeEnrollment()
        );

        assertThat(session.isPaid()).isFalse();
    }

    @Test
    @DisplayName("수강 신청을 한다")
    void enroll() {
        Session session = getPaidSession(30);
        Payment payment = new Payment("payment1", 1L, 1L, 10000L);

        session.enroll(USER, payment);
        assertThatThrownBy(() -> session.enroll(USER, payment))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("무료 강의에 수강 신청을 한다")
    void enrollFreeSession() {
        SessionPeriod period = new SessionPeriod(START_DATE, END_DATE);
        SessionPrice price = new SessionPrice(SessionType.FREE, 0);
        SessionDetailInfo detailInfo = new SessionDetailInfo(period, price);
        SessionBasicInfo basicInfo = new SessionBasicInfo("강의 제목", THUMBNAIL);
        SessionInfo sessionInfo = new SessionInfo(basicInfo, detailInfo);
        Session session = new Session(
                new SessionId(1L, 1L),
                sessionInfo,
                new FreeEnrollment()
        );


        session.enroll(USER, null);
        assertThatThrownBy(() -> session.enroll(USER, null))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("수강 인원이 가득 찬 강의는 수강 신청이 불가능하다")
    void enrollFullSession() {
        Session session = getPaidSession(1);
        Payment payment = new Payment("payment1", 1L, 1L, 10000L);
        NsUser anotherUser = new NsUser(2L, "user2", "password", "name", "email");

        session.enroll(USER, payment);
        assertThatThrownBy(() -> session.enroll(anotherUser, payment))
                .isInstanceOf(IllegalStateException.class);
    }

    private static Session getPaidSession(int maxEnrollment) {
        SessionPeriod period = new SessionPeriod(START_DATE, END_DATE);
        SessionPrice price = new SessionPrice(SessionType.PAID, 10000);
        SessionDetailInfo detailInfo = new SessionDetailInfo(period, price);
        SessionBasicInfo basicInfo = new SessionBasicInfo("강의 제목", THUMBNAIL);
        SessionInfo sessionInfo = new SessionInfo(basicInfo, detailInfo);

        return new Session(
                new SessionId(1L, 1L),
                sessionInfo,
                new PaidEnrollment(maxEnrollment)
        );
    }
} 