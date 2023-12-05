package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    public static final Session JAVA = new Session(
            "JAVA",
            LocalDate.of(2023, 12, 1),
            LocalDate.of(2023, 12, 15),
            SessionImageTest.IMAGE_PNG,
            SessionChargeTest.FREE,
            SessionStatus.RECRUITING);
    public static final Session JAVA_TDD_17 = new Session(
            "JAVA_TDD",
            LocalDate.of(2023, 12, 1),
            LocalDate.of(2023, 12, 15),
            SessionImageTest.IMAGE_PNG,
            SessionChargeTest.CHARGE_1000,
            SessionStatus.RECRUITING);
    public static final Session JAVA_TDD_16 = new Session(
            "JAVA_TDD",
            LocalDate.of(2023, 11, 1),
            LocalDate.of(2023, 11, 15),
            SessionImageTest.IMAGE_PNG,
            SessionChargeTest.CHARGE_1000,
            SessionStatus.END);
    public static final Session COMPUTER = new Session(
            "COMPUTER",
            LocalDate.of(2023, 12, 1),
            LocalDate.of(2023, 12, 15),
            SessionImageTest.IMAGE_JPG,
            SessionChargeTest.CHARGE_100,
            SessionStatus.RECRUITING);

    @DisplayName("시작일, 종료일, 이미지, 가격, 상태를 전달하면 강의 객체를 생성한다.")
    @Test
    void sessionTest() {
        assertThat(new Session("강의1", LocalDate.of(2023, 11, 1),
                LocalDate.of(2023, 11, 30),
                SessionImageTest.IMAGE_JPG,
                SessionChargeTest.FREE,
                SessionStatus.END)).isInstanceOf(Session.class);
        assertThat(new Session("강의2", LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 31),
                SessionImageTest.IMAGE_PNG,
                SessionChargeTest.CHARGE_1000,
                SessionStatus.RECRUITING)).isInstanceOf(Session.class);
    }

    @DisplayName("모집중이지 않은 강의를 신청할 시 IllegalStateException을 던진다.")
    @Test
    void addStudentException() {
        assertThatThrownBy(() -> JAVA_TDD_16.addStudent())
                .isInstanceOf(IllegalStateException.class);
    }
}
