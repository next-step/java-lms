package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    static Stream<Arguments> owner_test() {
        return Stream.of(
                Arguments.arguments(A1, NsUserTest.JAVAJIGI, true),
                Arguments.arguments(A2, NsUserTest.JAVAJIGI, false)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "owner_test")
    void is_owner(Answer answer, NsUser user, boolean isOwner) {
        assertThat(answer.isOwner(user)).isEqualTo(isOwner);
    }

    @Test
    void delete_answers() throws CannotDeleteException {
        DeleteHistory deleted = A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleted).isEqualTo(new DeleteHistory(A1));
    }
}
