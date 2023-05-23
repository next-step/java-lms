package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_성공() throws CannotDeleteException {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void delete_실패_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_성공_질문자_답변자_같음() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents2");

        question.addAnswer(answer1);
        question.addAnswer(answer2);

        List<DeleteHistory> deleteHistory = question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
        assertThat(deleteHistory.size()).isEqualTo(3);
    }

    @Test
    void delete_실패_질문자_답변자_다름() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answer answer2 = new Answer(NsUserTest.SANJIGI, question, "Answers Contents2");

        question.addAnswer(answer1);
        question.addAnswer(answer2);

        assertThatThrownBy(() -> {
            question.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}