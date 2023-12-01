package nextstep.courses.domain;

import nextstep.courses.domain.type.ApplyStatus;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApplyTest {

    @Test
    @DisplayName("승인할 수 있다")
    public void approve() {
        Apply apply = new Apply(session(), NsUser.GUEST_USER);
        apply.approve();
        assertThat(apply.status()).isEqualTo(ApplyStatus.APPROVAL);
    }

    @Test
    @DisplayName("거절할 수 있다")
    public void refuse() {
        Apply apply = new Apply(session(), NsUser.GUEST_USER);
        apply.refuse();
        assertThat(apply.status()).isEqualTo(ApplyStatus.REFUSAL);
    }

    public Session session() {
        Duration duration = new Duration(LocalDate.now(), LocalDate.now());
        Image image = new Image(1, "JPG", 300, 200);
        return new FreeSession(duration, new Images(Arrays.asList(image)));
    }

}
