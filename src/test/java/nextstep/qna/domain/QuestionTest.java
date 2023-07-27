package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        answer = AnswerTest.A1;
        question.addAnswer(answer);
    }

    @Test
    public void delete_성공() throws Exception {
        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {

        assertThatThrownBy(() -> {
            question.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        question.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> {
            question.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(RuntimeException.class);
    }
}
