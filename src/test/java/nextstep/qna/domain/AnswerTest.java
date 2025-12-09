package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    void 요청가자_답변작성자와_동일한지_확인할_수_있다() {
        long requesterId = 1L;
        Question question = new Question(1L, "title1", "contents1");
        Answer answer = new Answer(requesterId, question, "Answers Contents1");

        assertThat(answer.isOwner(requesterId)).isTrue();
    }
}
