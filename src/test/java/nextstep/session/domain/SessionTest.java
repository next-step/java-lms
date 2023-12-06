package nextstep.session.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SessionTest {

    private CoverImage chargeCoverImage = new CoverImage(1L, 1L, "charge.jpg", 2048, 300, 200);
    private CoverImage freeCoverImage = new CoverImage(2L,  2L, "free.jpg", 2048, 300, 200);

    private SessionDate chargeSessionDate = new SessionDate("2023-12-01 00:00:00", "2023-12-31 24:00:00");
    private SessionDate freeSessionDate = new SessionDate("2023-12-01 00:00:00", "2023-12-31 24:00:00");

    private Session chargeSession = new Session(1L, 1L, chargeSessionDate, SessionType.CHARGE, 100000, 1, chargeCoverImage, SessionStatus.PREPARE);

    private Session freeSession = new Session(2L, 1L, freeSessionDate, SessionType.FREE, 0, 1, freeCoverImage, SessionStatus.PREPARE);


    @Test
    void 수강생_추가() {

        NsUser student1 = new NsUser(1L, "aaa", "111", "aaa", "email11");
        NsUser student2 = new NsUser(2L, "bbb", "222", "bbb", "email1s");
        assertThatIllegalArgumentException().isThrownBy(() -> chargeSession.addStudent(student1, 50000)).withMessageMatching("수강신청은 모집중일때만 가능합니다.");

        chargeSession.setSessionStatus(SessionStatus.OPEN);
        assertThatIllegalArgumentException().isThrownBy(() -> chargeSession.addStudent(student1, 50000)).withMessageMatching("수강료가 일치하지 않습니다.");

        assertThatNoException().isThrownBy(() -> chargeSession.addStudent(student1, 100000));
        assertThat(chargeSession.students().size()).isEqualTo(1);

        assertThatIllegalArgumentException().isThrownBy(() -> chargeSession.addStudent(student2, 100000)).withMessageMatching("수강생이 초과되었습니다.");

    }
}