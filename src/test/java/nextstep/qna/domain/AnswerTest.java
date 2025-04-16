package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {

    public static Answer createAnswer(NsUser user, Question question) {
        return new Answer(user, question, "contents");
    }

    @Test
    @DisplayName("답변을 생성하면 질문의 답변 리스트에 들어간다.")
    void addAnswer() {
        Question question = QuestionTest.createQuestion(NsUserTest.JAVAJIGI);
        Answer answer = AnswerTest.createAnswer(NsUserTest.JAVAJIGI, question);

        assertThat(question.getAnswers()).contains(answer);
    }

    @Test
    @DisplayName("답변을 삭제하면, 답변 삭제 히스토리를 반환한다.")
    void deleteAndReturnHistory() {
        Question question = QuestionTest.createQuestion(NsUserTest.JAVAJIGI);
        Answer answer = AnswerTest.createAnswer(NsUserTest.JAVAJIGI, question);

        assertThat(answer.delete()).isNotNull()
                .hasFieldOrPropertyWithValue("contentType", ContentType.ANSWER)
                .hasFieldOrPropertyWithValue("deletedBy", NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("답변을 삭제하면, 삭제 상태가 변경된다.")
    void deleteAndUpdateDeleteStatus() {
        Question question = QuestionTest.createQuestion(NsUserTest.JAVAJIGI);
        Answer answer = AnswerTest.createAnswer(NsUserTest.JAVAJIGI, question);

        answer.delete();
        assertThat(answer.isDeleted()).isTrue();
    }
}
