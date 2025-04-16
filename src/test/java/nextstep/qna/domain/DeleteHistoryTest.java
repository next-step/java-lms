package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

public class DeleteHistoryTest {
    public Question question;
    public Answer answer1;
    public Answer answer2;
    public Answers answers = new Answers();

    @BeforeEach
    void setUp() {
        question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        answer1 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        answer2 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents2");
        answers.add(answer1);
        answers.add(answer2);
        question.addAnswer(answer1);
        question.addAnswer(answer2);
    }

    @Test
    void 답변_삭제_테스트() {
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(),
            LocalDateTime.now());
        DeleteHistory history = answer1.deleteBy(NsUserTest.JAVAJIGI);
        assertEquals(deleteHistory, history);
    }

    @Test
    void 복수_답변_삭제_테스트() {
        List<DeleteHistory> deleteHistoriesSample = List.of(
            new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), LocalDateTime.now())
        );
        List<DeleteHistory> deleteHistories = answers.deleteAllBy(NsUserTest.JAVAJIGI);
        assertThat(deleteHistories).containsAll(deleteHistoriesSample);
    }

    @Test
    void 질문_삭제_테스트() {
        List<DeleteHistory> deleteHistoriesSample = List.of(
            new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), LocalDateTime.now())
        );
        List<DeleteHistory> deleteHistories = question.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(deleteHistories).containsAll(deleteHistoriesSample);
    }
}
