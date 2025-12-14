package nextstep.users.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LogInPairKeyTest {

    @Test
    void 아아디_비밀번호가_일치하는지_검증할_수_있다() {
        assertThat(
                new LogInPairKey("min", "6038").matchUser("min", "6038")
        ).isTrue();
    }

    @Test
    void 아아디_비밀번호중_아이디가_불일치하는지_검증할_수_있다() {
        assertThat(
                new LogInPairKey("min", "6038").matchUser("diff min", "6038")
        ).isFalse();
    }

    @Test
    void 아아디_비밀번호중_비밀번호가_불일치하는지_검증할_수_있다() {
        assertThat(
                new LogInPairKey("min", "6038").matchUser("min", "1234")
        ).isFalse();
    }
}