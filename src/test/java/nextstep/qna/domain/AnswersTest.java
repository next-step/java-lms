package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class AnswersTest {

    @Test
    void 답변_모두_삭제() {

        Answers answers = new Answers();

        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        List<DeleteHistory> deleteHistories = answers.deleteAll();

        Assertions.assertThat(deleteHistories)
                .contains(new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));
        Assertions.assertThat(deleteHistories)
                .contains(new DeleteHistory(ContentType.ANSWER, AnswerTest.A2.getId(), NsUserTest.SANJIGI, LocalDateTime.now()));
    }
}
