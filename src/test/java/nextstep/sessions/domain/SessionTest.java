package nextstep.sessions.domain;

import nextstep.common.PeriodTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    public static final Session JAVA = new Session(
            "JAVA",
            PeriodTest.DEC,
            SessionImageTest.IMAGE_PNG,
            SessionChargeTest.FREE,
            SessionStatus.RECRUITING);
    public static final Session JAVA_TDD_17 = new Session(
            "JAVA_TDD",
            PeriodTest.DEC,
            SessionImageTest.IMAGE_PNG,
            SessionChargeTest.CHARGE_1000,
            SessionStatus.RECRUITING);
    public static final Session JAVA_TDD_16 = new Session(
            "JAVA_TDD",
            PeriodTest.NOV,
            SessionImageTest.IMAGE_PNG,
            SessionChargeTest.CHARGE_1000,
            SessionStatus.END);
    public static final Session COMPUTER = new Session(
            "COMPUTER",
            PeriodTest.DEC,
            SessionImageTest.IMAGE_JPG,
            SessionChargeTest.CHARGE_100,
            SessionStatus.RECRUITING);

    @DisplayName("시작일, 종료일, 이미지, 가격, 상태를 전달하면 강의 객체를 생성한다.")
    @Test
    void sessionTest() {
        assertThat(new Session("강의1",
                PeriodTest.NOV,
                SessionImageTest.IMAGE_JPG,
                SessionChargeTest.FREE,
                SessionStatus.END)).isInstanceOf(Session.class);
        assertThat(new Session("강의2",
                PeriodTest.DEC,
                SessionImageTest.IMAGE_PNG,
                SessionChargeTest.CHARGE_1000,
                SessionStatus.RECRUITING)).isInstanceOf(Session.class);
    }

    @DisplayName("진행중이 아니거나 모집중이 아닌 강의는 IllegalStateException을 던진다.")
    @Test
    void checkSessionStatusExceptionTest() {
        Session session1 = new Session("강의1", PeriodTest.NOV, SessionImageTest.IMAGE_PNG, SessionChargeTest.CHARGE_100, SessionStatus.RECRUITING);
        Session session2 = new Session("강의1", PeriodTest.DEC, SessionImageTest.IMAGE_PNG, SessionChargeTest.CHARGE_100, SessionStatus.END);

        assertThatThrownBy(() -> session1.checkSessionStatus())
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> session2.checkSessionStatus())
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("모집 인원이 마감되지 않은 강의는 학생을 추가할 수 있다.")
    @Test
    void addStudentTest() {
        Session session = new Session("강의", PeriodTest.DEC, SessionImageTest.IMAGE_JPG, SessionChargeTest.CHARGE_100, SessionStatus.RECRUITING);
        int studentCount = session.getStudentCount();
        session.addStudent();

        assertThat(session.getStudentCount()).isEqualTo(studentCount + 1);
    }

    @DisplayName("모집 인원이 마감된 강의는 학생을 추가하면 IllegalStateException을 던진다.")
    @Test
    void addStudentExceptionTest() {
        Session session = new Session("강의2", PeriodTest.DEC, SessionImageTest.IMAGE_PNG, SessionChargeTest.CHARGE_100, SessionStatus.RECRUITING);
        session.addStudent();

        assertThatThrownBy(() -> session.addStudent())
                .isInstanceOf(IllegalStateException.class);
    }
}
