package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    private static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private static final Answer A2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("다수의 답변을 삭제하고 삭제 이력을 반환한다")
    @Test
    void bulkDelete() throws CannotDeleteException {
        Answers answers = new Answers(List.of(AnswersTest.A1, AnswersTest.A2));

        List<DeleteHistory> histories = answers.delete(NsUserTest.JAVAJIGI);

        assertThat(histories).extracting("contentType", "deletedBy")
                .containsExactlyInAnyOrder(
                        new Tuple(ContentType.ANSWER, NsUserTest.JAVAJIGI),
                        new Tuple(ContentType.ANSWER, NsUserTest.JAVAJIGI)
                );
    }
}
