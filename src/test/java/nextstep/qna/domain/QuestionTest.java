package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @BeforeEach
    void setting() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);
        Q1.addAnswers(AnswerTest.A1);
        Q1.addAnswers(AnswerTest.A2);
    }

    @DisplayName("본인이 질문한 질문이 아닌데 삭제하려고 할 경우 CannotDeleteException 발생")
    @Test
    void 다른사람_질문을_삭제하려면_예외발생() {
        assertThatThrownBy(() -> Q1.remove(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문에 답변자가 다른사람이 한명이라도 있다면 CannotDeleteException 발생")
    @Test
    void 다른_답변이_있으면_삭제시_예외발생() {
        assertThatThrownBy(() -> Q1.remove(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("질문을 삭제하면 List<DeleteHistory>를 리턴")
    @Test
    void 질문_삭제() throws CannotDeleteException {
        //given
        Question question = new Question(NsUserTest.SANJIGI, "testTitle", "testContents");
        Answer answer1 = new Answer(NsUserTest.SANJIGI, question, "testAnswer1");
        Answer answer2 = new Answer(NsUserTest.SANJIGI, question, "testAnswer2");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        //when
        List<DeleteHistory> deleteHistories = question.remove(NsUserTest.SANJIGI);

        //then
        assertThat(deleteHistories).containsExactly(
                new DeleteHistory(ContentType.QUESTION, question.getId(), NsUserTest.SANJIGI, LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer1.getId(), NsUserTest.SANJIGI, LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer2.getId(), NsUserTest.SANJIGI, LocalDateTime.now())
        );
    }
}
