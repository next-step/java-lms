package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    @Test
    @DisplayName("실패 - delete 메서드가 질문을 삭제할 권한이 없을 때 예외가 발생한다.")
    void throwExceptionWhen() {
        assertThatThrownBy(() -> createQuestion(NsUserTest.JAVAJIGI).delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("성공 - delete 메서드가 질문을 삭제할 권한이 있을 때 정상 처리된다.")
    void deleteTest() throws Exception{
        Question question = createQuestion(NsUserTest.JAVAJIGI);
        question.delete(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("실패 - delete 메서드가 다른 사람이 쓴 답변이 있을 때 예외가 발생한다.")
    void throwExceptionWhen2() {
        Question question = createQuestion(NsUserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.createAnswer(NsUserTest.SANJIGI, question));
        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("성공 - delete 메서드가 다른 사람이 쓴 답변이 없을 때 정상 처리된다.")
    void deleteTest2() throws Exception{
        Question question = createQuestion(NsUserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.createAnswer(NsUserTest.JAVAJIGI, question));
        question.delete(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    public static Question createQuestion(NsUser user) {
        return new Question(user, "title1", "contents1");
    }
}
