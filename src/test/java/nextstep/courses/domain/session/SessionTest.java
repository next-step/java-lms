package nextstep.courses.domain.session;

import nextstep.courses.domain.session.enrollment.Enrollments;
import nextstep.courses.domain.session.enrollment.FreeEnrollments;
import nextstep.courses.domain.session.enrollment.PaidEnrollments;
import nextstep.courses.domain.session.info.SessionInfo;
import nextstep.courses.domain.session.info.basic.SessionBasicInfo;
import nextstep.courses.domain.session.info.basic.SessionThumbnail;
import nextstep.courses.domain.session.info.detail.SessionDetailInfo;
import nextstep.courses.domain.session.info.detail.SessionPeriod;
import nextstep.courses.domain.session.info.detail.SessionPrice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {
    private static final LocalDate START_DATE = LocalDate.now();
    private static final LocalDate END_DATE = START_DATE.plusMonths(1);
    public static final SessionThumbnail THUMBNAIL = new SessionThumbnail();

    @BeforeAll
    static void setUp() {
        THUMBNAIL.addThumbnail("image.jpg", 1024, 300, 200);
    }

    @Test
    @DisplayName("강의를 생성한다")
    void create() {
        SessionPeriod period = new SessionPeriod(START_DATE, END_DATE);
        SessionPrice price = new SessionPrice(SessionType.PAID, 10000);
        SessionDetailInfo detailInfo = new SessionDetailInfo(period, price);
        SessionBasicInfo basicInfo = new SessionBasicInfo("강의 제목", THUMBNAIL);
        SessionInfo sessionInfo = new SessionInfo(basicInfo, detailInfo, 30);
        Session session = SessionTestData.defaultSession()
                .info(sessionInfo)
                .build();

        assertThat(session.isPaid()).isTrue();
    }

    @Test
    @DisplayName("무료 강의를 생성한다")
    void createFreeSession() {
        SessionPeriod period = new SessionPeriod(START_DATE, END_DATE);
        SessionPrice price = new SessionPrice(SessionType.FREE, 0);
        SessionDetailInfo detailInfo = new SessionDetailInfo(period, price);
        SessionBasicInfo basicInfo = new SessionBasicInfo("강의 제목", THUMBNAIL);
        SessionInfo sessionInfo = new SessionInfo(basicInfo, detailInfo, 0);
        Session session = SessionTestData.defaultSession()
                .info(sessionInfo)
                .build();

        assertThat(session.isPaid()).isFalse();
    }

    @Test
    @DisplayName("유료 강의의 Enrollments를 생성한다")
    void createPaidEnrollments() {
        SessionPeriod period = new SessionPeriod(START_DATE, END_DATE);
        SessionPrice price = new SessionPrice(SessionType.PAID, 10000);
        SessionDetailInfo detailInfo = new SessionDetailInfo(period, price);
        SessionBasicInfo basicInfo = new SessionBasicInfo("강의 제목", THUMBNAIL);
        SessionInfo sessionInfo = new SessionInfo(basicInfo, detailInfo, 30);
        Session session = SessionTestData.defaultSession()
                .info(sessionInfo)
                .build();

        Enrollments enrollments = session.createEnrollments();
        assertThat(enrollments).isInstanceOf(PaidEnrollments.class);
    }

    @Test
    @DisplayName("무료 강의의 Enrollments를 생성한다")
    void createFreeEnrollments() {
        SessionPeriod period = new SessionPeriod(START_DATE, END_DATE);
        SessionPrice price = new SessionPrice(SessionType.FREE, 0);
        SessionDetailInfo detailInfo = new SessionDetailInfo(period, price);
        SessionBasicInfo basicInfo = new SessionBasicInfo("강의 제목", THUMBNAIL);
        SessionInfo sessionInfo = new SessionInfo(basicInfo, detailInfo, 0);
        Session session = SessionTestData.defaultSession()
                .info(sessionInfo)
                .build();

        Enrollments enrollments = session.createEnrollments();
        assertThat(enrollments).isInstanceOf(FreeEnrollments.class);
    }

    @Test
    @DisplayName("세션에 여러 썸네일을 추가할 수 있다")
    void addThumbnails() {
        SessionThumbnail thumbnail = new SessionThumbnail();
        thumbnail.addThumbnail("test1.jpg", 1024L, 300, 200);
        thumbnail.addThumbnail("test2.jpg", 1024L, 300, 200);

        SessionPeriod period = new SessionPeriod(START_DATE, END_DATE);
        SessionPrice price = new SessionPrice(SessionType.PAID, 10000);
        SessionDetailInfo detailInfo = new SessionDetailInfo(period, price);
        SessionBasicInfo basicInfo = new SessionBasicInfo("강의 제목", thumbnail);
        SessionInfo sessionInfo = new SessionInfo(basicInfo, detailInfo, 30);
        Session session = SessionTestData.defaultSession()
                .info(sessionInfo)
                .build();

        assertThat(session.getInfo().getBasicInfo().getThumbnail().getThumbnails()).hasSize(2);
    }
}

class SessionTestData {
    static Session.SessionBuilder defaultSession() {
        return Session.builder()
                .id(new SessionId(1L, 1L));
    }
}