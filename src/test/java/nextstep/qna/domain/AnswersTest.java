package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    @Test
    @DisplayName("성공 : 답변 삭제 테스트")
    void deleteTest() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.delete();
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("성공 : 질문 작성자와 답변 작성자 동일 체크")
    void containsNotOwnerTest() throws CannotDeleteException {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        assertThat(answers.containsNotOwner(NsUserTest.JAVAJIGI)).isFalse();
    }
}