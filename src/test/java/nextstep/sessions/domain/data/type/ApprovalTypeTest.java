package nextstep.sessions.domain.data.type;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApprovalTypeTest {

    @Test
    void valueOfCode() {
        assertThat(ApprovalType.valueOfCode("Y")).isEqualTo(ApprovalType.APPROVAL);
        assertThat(ApprovalType.valueOfCode("N")).isEqualTo(ApprovalType.BEFORE_APPROVAL);
    }
}
