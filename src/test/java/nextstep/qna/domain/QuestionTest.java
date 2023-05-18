package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    private Question question;

    @BeforeEach
    public void setUp() {
        question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
    }

    @DisplayName("질문 작성자와 삭제시도 유저가 다르다.")
    @Test
    void test1() {
        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("다른 사람이 작성한 답변이 있다.")
    @Test
    void test2() {
        question.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer1"));
        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문/답변 삭제 성공")
    @Test
    void test3() throws CannotDeleteException {
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "answer1");
        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, question, "answer2");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
    }
}
