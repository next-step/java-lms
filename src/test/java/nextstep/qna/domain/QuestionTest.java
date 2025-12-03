package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    private Question q1;
    private Answer a1;
    private Answer a2;

    @BeforeEach
    void setUp() {
        q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        a1 = new Answer(NsUserTest.JAVAJIGI, q1, "answer content1");
        a2 = new Answer(NsUserTest.SANJIGI, q1, "answer content2");
    }

    @Test
    @DisplayName("답변이 없는 질문은 요청자와 작성자가 같은 경우 삭제한다.")
    void deleteQuestion() throws Exception {
        q1.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문과 답변의 작성자가 같고 요청자도 경우, 질문과 답변 모두 삭제한다.")
    void deleteQuestionAnswers() throws Exception {
        q1.addAnswer(a1);
        q1.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(q1.isDeleted()).isTrue();
        assertThat(a1.isDeleted()).isTrue();
    }


    @Test
    @DisplayName("질문과 답변의 작성자가 다른 경우, 질문과 답변 모두 삭제되지 않는다.")
    void invalidDeleteQuestionAnswers() {
        q1.addAnswer(a2);
        assertThatThrownBy(() -> q1.deleteBy(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 히스토리로 생성 시 정상적으로 질문과 답변 모두 삭제 히스토리로 변환된다.")
    void toDeleteHistory() throws Exception {
        q1.addAnswer(a1);
        q1.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(q1.toDeleteHistories()).hasSize(2);
    }
}
