package nextstep.qna.domain.step2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SessionTest {

    private Session chargeSession = new Session(new SessionDate("2023-12-01", "2023-12-31"), SessionType.CHARGE, 100000, 1);
    private Session freeSession = new Session(new SessionDate("2023-12-01", "2023-12-31"), SessionType.FREE, 0, 1);

    @Test
    void 커버이미지() {
        String path = "src/test/resources/test.aaa";
        assertThatIllegalArgumentException().isThrownBy(() -> chargeSession.setCoverImage(path));
    }

    @Test
    void 수강생_추가() {

        Payment payment = new Payment(5000);
        assertThatIllegalArgumentException().isThrownBy(() -> chargeSession.addStudent(new Student("aaa", "1"), payment.price())).withMessageMatching("수강신청은 모집중일때만 가능합니다.");

        chargeSession.setSessionStatus(SessionStatus.OPEN);
        assertThatIllegalArgumentException().isThrownBy(() -> chargeSession.addStudent(new Student("aaa", "1"), 5000)).withMessageMatching("수강료가 일치하지 않습니다.");

        Payment payment2 = new Payment(100000);
        assertThatNoException().isThrownBy(() -> chargeSession.addStudent(new Student("aaa", "1"), payment2.price()));
        assertThat(chargeSession.students().size()).isEqualTo(1);

        assertThatIllegalArgumentException().isThrownBy(() -> chargeSession.addStudent(new Student("aaa", "1"), payment2.price())).withMessageMatching("수강생이 초과되었습니다.");

    }
}