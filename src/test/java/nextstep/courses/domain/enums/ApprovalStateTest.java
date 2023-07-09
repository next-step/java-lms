package nextstep.courses.domain.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApprovalStateTest {

    @Test
    void 동일비교 () {
        assertThat(ApprovalState.APPROVED).isEqualTo(ApprovalState.APPROVED);
    }

    @Test
    void 동치비교 () {
        assertThat(ApprovalState.of(1)).isEqualTo(ApprovalState.APPROVED);
    }

    @Test
    void 미존재값조회() {
        assertThatThrownBy(() -> {
            ApprovalState approvalState = ApprovalState.of(10);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid ApprovalState value: 10");
    }
}
