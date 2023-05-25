package nextstep.study;

import nextstep.courses.domain.Session;
import nextstep.support.ProxyUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProxyTest {
    @Test
    @DisplayName("create 학습 테스트")
    void test01() {
        Session session = ProxyUtils.create(Session.class, 1L);

        assertThat(ProxyUtils.isProxy(session)).isTrue();
        assertThat(session.getId()).isEqualTo(1L);
    }
}
