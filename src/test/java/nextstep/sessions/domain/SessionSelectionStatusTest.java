package nextstep.sessions.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionSelectionStatusTest {
    @Test
    void testSelectable() {
        assertThat(SessionSelectionStatus.SELECTED.isSelectable()).isTrue();
        assertThat(SessionSelectionStatus.NOT_SELECTED.isSelectable()).isFalse();
    }
}
