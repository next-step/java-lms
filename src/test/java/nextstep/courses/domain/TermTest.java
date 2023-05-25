package nextstep.courses.domain;

import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TermTest {

    @BeforeEach
    public void setUp() {
        TestFixture.fixtureInit();
    }

    @DisplayName("기수별로 세션을 갖는다")
    @Test
    public void term() {
        //given
        Term term = TestFixture.TERM16;
        Session session1 = TestFixture.LIME_SESSION;
        Session session2 = TestFixture.LEMON_SESSION;
        //when
        term.addSessions(session1, session2);
        //then
        assertThat(term.includeSession(session1)).isTrue();
        assertThat(term.includeSession(session2)).isTrue();
    }
}