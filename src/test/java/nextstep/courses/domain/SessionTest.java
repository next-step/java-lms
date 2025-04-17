package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class SessionTest {

    Session session = new Session();

    @Test
    void 시작일을_가진다() {
        assertThat(session.getStartDt()).isNotNull();
    }

    @Test
    void 종료일을_가진다() {
        assertThat(session.getEndDt()).isNotNull();
    }

    @Test
    void 강의커버_이미지를_가진다() {
        assertThat(session.getCoverImage()).isNotNull();
    }


    @Test
    void 유료강의() {
        session.asPaid(1, BigDecimal.valueOf(1000));
        assertThat(session.isAvailable()).isTrue();
        session.apply(new NsUser());
        assertThat(session.isAvailable()).isFalse();
    }

    @Test
    void 무료강의() {
        session.asFree();
        assertThat(session.isAvailable()).isTrue();
    }

    @Test
    void 수강생이_결제한_금액과_수강료가_일치(){
        session.asPaid(10, BigDecimal.valueOf(800_000));
        assertThat(session.isExactCost(BigDecimal.valueOf(800_000))).isTrue();
    }

    @Test
    void 수강생이_결제한_금액과_수강료가_불일치(){
        session.asPaid(10, BigDecimal.valueOf(800_000));
        assertThat(session.isExactCost(BigDecimal.valueOf(600_000))).isFalse();
    }

    @Test
    void 강의_상태는_준비중_상태를_가진다(){
        session.asFree();
        assertThat(session.getSessionStatus()).isEqualTo(SessionStatus.READY);
    }

    @Test
    void 강의_상태는_모집중_상태를_가진다(){
        session.asFree();
        session.open();
        assertThat(session.getSessionStatus()).isEqualTo(SessionStatus.OPEN);
    }

    @Test
    void 강의_상태는_종료_상태를_가진다(){
        session.asFree();
        session.close();
        assertThat(session.getSessionStatus()).isEqualTo(SessionStatus.CLOSE);
    }
}