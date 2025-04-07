package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");


    @Test
    void delete_성공_빈_질문() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        List<DeleteHistory> deleteHistories = question.delete(NsUserTest.JAVAJIGI);
        assertThat(deleteHistories).containsExactly(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now())
        );
    }

    @Test
    void delete_성공_질문자와_같은_답변자() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        question.addAnswer(answer);

        List<DeleteHistory> deleteHistories = question.delete(NsUserTest.JAVAJIGI);
        assertThat(deleteHistories).containsExactly(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())
        );
    }

    @Test
    void delete_실패_권한() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        question.addAnswer(answer);

        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void delete_실패() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(NsUserTest.SANJIGI, question, "Answers Contents1");
        question.addAnswer(answer);

        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

}
