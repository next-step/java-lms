package nextstep.qna.domain.deleteHistory;

import nextstep.qna.domain.ContentType;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionTest;
import nextstep.qna.domain.answer.Answer;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeleteHistoriesTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    @Test
    void createDeleteHistory() {
        Q1.addAnswer(A1);
        DeleteHistories deleteHistories = new DeleteHistories(Q1.getDeleteHistories());

        assertThat(deleteHistories).isEqualTo(new DeleteHistories(
                List.of(new DeleteHistory(ContentType.QUESTION, 0L, Q1.getWriter(), LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()))
        ));
    }

}
