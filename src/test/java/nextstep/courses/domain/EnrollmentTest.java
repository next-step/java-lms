package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EnrollmentTest {

    @Test
    void 생성자테스트() {
        NsUser jerry = new NsUser(1L, "jerry", "password", "name", "jerry@nextstep.com");
        Assertions.assertThat(new Enrollment(jerry,1L)).isInstanceOf(Enrollment.class);
    }
}
