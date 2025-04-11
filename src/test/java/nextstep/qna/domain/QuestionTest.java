package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    private Question Q3;
    private Question Q4;
    private DeleteHistories DELETE_HISTORIES;

    @BeforeEach
    void setUp(){
        Q3 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q4 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
        DELETE_HISTORIES = new DeleteHistories();
    }

    @Test
    void shouldNotAllowDelete_WhenUserIsNotOwner(){
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(()->Q3.delete(NsUserTest.SANJIGI, DELETE_HISTORIES))
                .withMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void shouldAllowDelete_WhenUserIsOwnerWithoutAnswer() throws CannotDeleteException {
        Q3.delete(NsUserTest.JAVAJIGI, DELETE_HISTORIES);
        assertThat(Q3.isDeleted()).isTrue();
        assertThat(DELETE_HISTORIES.getDeleteHistories().size()).isEqualTo(1);
    }

    @Test
    void shouldNotAllowDelete_WhenAnswerOwnerIsNotMatched() throws CannotDeleteException {
        Q1.addAnswer(A2);
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(()->Q1.delete(NsUserTest.JAVAJIGI, DELETE_HISTORIES))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void shouldDelete_WhenAnswerOwnerIsAlsoMatched() throws CannotDeleteException {
        Q3.addAnswer(A1);
        Q3.delete(NsUserTest.JAVAJIGI, DELETE_HISTORIES);
        assertThat(Q3.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isTrue();
        assertThat(DELETE_HISTORIES.getDeleteHistories().size()).isEqualTo(2);
    }
}
