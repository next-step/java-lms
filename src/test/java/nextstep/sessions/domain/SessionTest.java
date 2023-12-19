package nextstep.sessions.domain;

import nextstep.common.PeriodTest;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @DisplayName("시작일, 종료일, 이미지, 가격, 상태를 전달하면 강의 객체를 생성한다.")
    @Test
    void sessionTest() {
        assertThat(new Session("강의1",
                PeriodTest.NOV,
                new SessionImages(List.of(SessionImageTest.IMAGE_JPG)),
                SessionChargeTest.FREE,
                SessionStatus.END)).isInstanceOf(Session.class);
        assertThat(new Session("강의2",
                PeriodTest.DEC,
                new SessionImages(List.of(SessionImageTest.IMAGE_PNG)),
                SessionChargeTest.CHARGE_1000,
                SessionStatus.RECRUITING)).isInstanceOf(Session.class);
    }

    @DisplayName("진행중이 아니거나 모집중이 아닌 강의는 IllegalStateException을 던진다.")
    @Test
    void checkSessionStatusExceptionTest() {
        Session session1 = new Session("강의1", PeriodTest.NOV, new SessionImages(List.of(SessionImageTest.IMAGE_PNG)), SessionChargeTest.CHARGE_100, SessionStatus.RECRUITING);
        Session session2 = new Session("강의1", PeriodTest.DEC, new SessionImages(List.of(SessionImageTest.IMAGE_PNG)), SessionChargeTest.CHARGE_100, SessionStatus.END);

        assertThatThrownBy(() -> session1.checkSessionStatus())
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> session2.checkSessionStatus())
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("모집 인원이 마감된 강의는 수강신청을 하면 IllegalStateException을 던진다.")
    @Test
    void addStudentExceptionTest() {
        Session session = new Session("강의2", PeriodTest.DEC, new SessionImages(List.of(SessionImageTest.IMAGE_PNG)), SessionChargeTest.CHARGE_100, SessionStatus.RECRUITING);
        session.enroll(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> session.enroll(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("커버 이미지를 추가할 수 있다.")
    @Test
    void addImageTest() {
        List<SessionImage> images = new ArrayList<>();
        images.add(SessionImageTest.IMAGE_JPG);
        Session session = new Session("강의1", PeriodTest.NOV, new SessionImages(images), SessionChargeTest.CHARGE_100, SessionStatus.RECRUITING);
        int beforeTotalCount = session.getImages().size();
        session.addImage(SessionImageTest.IMAGE_PNG);

        assertThat(session.getImages().size()).isEqualTo(beforeTotalCount + 1);
    }
}
