package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    private Question question;

    @BeforeEach
    void setup() {
        question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
    }

    @Test
    void delete() throws Exception {
        question.addAnswer(new Answer(1L, NsUserTest.JAVAJIGI, question, "Answers Contents1"));
        List<DeleteHistory> deleteHistories = question.delete(NsUserTest.JAVAJIGI);
        assertThat(deleteHistories)
                .contains(new DeleteHistory(ContentType.QUESTION, 1L, NsUserTest.JAVAJIGI, LocalDateTime.now()))
                .contains(new DeleteHistory(ContentType.ANSWER, 1L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    void delete_질문_작성자가_아니면_CannotDeleteException() {
        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_질문에_다른사람_답변이_있으면_CannotDeleteException() {
        question.addAnswer(new Answer(1L, NsUserTest.SANJIGI, question, "Answers Contents1"));
        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
