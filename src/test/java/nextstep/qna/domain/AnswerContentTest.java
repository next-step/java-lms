package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class AnswerContentTest {

    @Test
    void validateAnswerContent_nsUser_테스트() {
        assertThatIllegalArgumentException().isThrownBy(() -> new AnswerContent(null, "contents"));
    }

    @ParameterizedTest
    @CsvSource(value = {"' '", "NULL"}, nullValues = "NULL")
    void validateAnswerContent_contents_테스트(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> new AnswerContent(new NsUser(), input));
    }

    @Test
    void equalsWriter_테스트() {
        NsUser nsUser = new NsUser(null, "userId", "password", "name", "email");
        assertThat(new AnswerContent(nsUser, "contents").equalsWriter(nsUser)).isTrue();
    }
}
