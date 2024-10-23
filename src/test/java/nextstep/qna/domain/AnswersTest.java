package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    @Test
    @DisplayName("실패 - 질문을 삭제할 때 다른 사람이 쓴 답변이 있으면 예외를 던진다")
    void throwExceptionWhenDeletingQuestionWithOtherUserAnswer() {
        NsUser owner = new NsUser(1L, "user_id1", "password", "홍길동", "test1@a.com");
        NsUser otherUser = new NsUser(2L, "user_id2", "password", "전우치", "test2@a.com");

        Question question = new Question(owner, "제목", "내용");

        Answer ownerAnswer = new Answer(owner, question, "답변1");
        Answer otherUserAnswer = new Answer(otherUser, question, "답변2");

        Answers answers = question.getAnswers();
        answers.add(ownerAnswer);
        answers.add(otherUserAnswer);

        assertThatThrownBy(() -> answers.validateDelete(owner))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}