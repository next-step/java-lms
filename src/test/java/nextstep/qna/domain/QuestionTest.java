package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);
    }



    @Test
    @DisplayName("question_다른_사람이_쓴_글_삭제")
    void delete_question_test(){
        assertThatThrownBy(()->Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("question_답변_존재하는_글_삭제시_히스토리_체크")
    void delete_question_has_answer_test() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));

        assertThat(Q1.delete(NsUserTest.JAVAJIGI)).isEqualTo(deleteHistories);
    }


}
