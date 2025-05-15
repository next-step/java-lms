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
    public static final SessionThumbnail THUMBNAIL = new SessionThumbnail();

    @BeforeAll
    static void setUp() {
        THUMBNAIL.addThumbnail("image.jpg", 1024, 300, 200);
    }

    @Test
    @DisplayName("강의를 생성한다")
    void create() {
        int maxEnrollment = 30;

        Session session = SessionTestData.defaultSession()
                .info(SessionTestData.defaultSessionInfo()
                        .detailInfo(SessionTestData.paidSessionDetailInfo())
                        .maxEnrollment(maxEnrollment)
                        .build())
                .build();

        assertThat(session.isPaid()).isTrue();
    }

    @Test
    @DisplayName("무료 강의를 생성한다")
    void createFreeSession() {
        Session session = SessionTestData.defaultSession()
                .info(SessionTestData.defaultSessionInfo()
                        .detailInfo(SessionTestData.freeSessionDetailInfo())
                        .build())
                .build();

        assertThat(session.isPaid()).isFalse();
    }

    @Test
    @DisplayName("유료 강의의 Enrollments를 생성한다")
    void createPaidEnrollments() {
        int maxEnrollment = 30;

        Session session = SessionTestData.defaultSession()
                .info(SessionTestData.defaultSessionInfo()
                        .detailInfo(SessionTestData.paidSessionDetailInfo())
                        .maxEnrollment(maxEnrollment)
                        .build())
                .build();

        Enrollments enrollments = session.createEnrollments();
        assertThat(enrollments).isInstanceOf(PaidEnrollments.class);
    }

    @Test
    @DisplayName("무료 강의의 Enrollments를 생성한다")
    void createFreeEnrollments() {
        Session session = SessionTestData.defaultSession()
                .info(SessionTestData.defaultSessionInfo()
                        .detailInfo(SessionTestData.freeSessionDetailInfo())
                        .build())
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

        SessionBasicInfo basicInfo = new SessionBasicInfo("강의 제목", thumbnail);
        int maxEnrollment = 30;

        Session session = SessionTestData.defaultSession()
                .info(SessionTestData.defaultSessionInfo()
                        .basicInfo(basicInfo)
                        .detailInfo(SessionTestData.freeSessionDetailInfo())
                        .maxEnrollment(maxEnrollment)
                        .build())
                .build();

        assertThat(session.getInfo().getBasicInfo().getThumbnail().getThumbnails()).hasSize(2);
    }
}

class SessionTestData {
    static Session.SessionBuilder defaultSession() {
        return Session.builder()
                .id(new SessionId(1L, 1L));
    }

    static SessionInfo.SessionInfoBuilder defaultSessionInfo() {
        return SessionInfo.builder()
                .basicInfo(new SessionBasicInfo("강의 제목", new SessionThumbnail()))
                .maxEnrollment(0);
    }

    static SessionDetailInfo freeSessionDetailInfo() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(1);
        SessionPrice price = new SessionPrice(SessionType.FREE, 0);

        return SessionDetailInfo.builder()
                .period(new SessionPeriod(startDate, endDate))
                .price(price)
                .build();
    }


    static SessionDetailInfo paidSessionDetailInfo() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(1);
        SessionPrice price = new SessionPrice(SessionType.PAID, 10000);

        return SessionDetailInfo.builder()
                .period(new SessionPeriod(startDate, endDate))
                .price(price)
                .build();
    }
}