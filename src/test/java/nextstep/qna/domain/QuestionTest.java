package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    public static Question createQuestion(NsUser user) {
        return new Question(user, "title", "contents");
    }

    @Test
    @DisplayName("본인이 쓴 질문이고, 답변이 없으면 삭제 가능하다.")
    void assertCanDeleteByQuestionWithNoAnswerByOwner() {
        Question question = createQuestion(NsUserTest.JAVAJIGI);
        assertThatCode(() -> question.assertCanDelete(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("다른 사람이 쓴 질문이면, 삭제 불가능하다.")
    void assertCanDeleteByQuestionByOther() {
        Question question = createQuestion(NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> question.assertCanDelete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("본인이 쓴 질문이고, 본인이 쓴 답변만 있으면 삭제 가능하다.")
    void assertCanDeleteByQuestionWithSelfAnswerByOwner() {
        Question question = createQuestion(NsUserTest.JAVAJIGI);
        AnswerTest.createAnswer(NsUserTest.JAVAJIGI, question);

        assertThatCode(() -> question.assertCanDelete(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("본인이 쓴 질문이고, 타인이 쓴 답변이 있으면 삭제 불가능하다.")
    void assertCanDeleteByQuestionWithOtherAnswerByOwner() {
        Question question = createQuestion(NsUserTest.JAVAJIGI);
        AnswerTest.createAnswer(NsUserTest.SANJIGI, question);

        assertThatThrownBy(() -> question.assertCanDelete(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }


    @Test
    @DisplayName("질문을 삭제하면, 삭제 상태가 변경된다.")
    void deleteAndUpdateDeleteByStatus() {
        Question question = createQuestion(NsUserTest.JAVAJIGI);
        Answer answer = AnswerTest.createAnswer(NsUserTest.JAVAJIGI, question);

        question.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

}
