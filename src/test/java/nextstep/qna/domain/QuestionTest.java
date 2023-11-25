package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문 삭제 시 질문 작성자가 아닐 경우 에러 발생한다")
    public void validate_is_owner() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> Q1.deleted(NsUserTest.SANJIGI))
            .withMessageMatching("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문 삭제 시 다른 작성자의 답변이 있을 경우 에러 발생한다")
    public void validate_answer_of_others() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> {
                Q2.addAnswer(AnswerTest.A1);
                Q2.deleted(NsUserTest.SANJIGI);
            })
            .withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("질문을 삭제 시 삭제 상태값은 true이다")
    public void delete_question() {
        Q1.deleted(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 삭제 시 히스토리를 생성할 수 있다")
    public void delete_question_history() {
        assertThat(Q1.deleted(NsUserTest.JAVAJIGI)).hasOnlyElementsOfType(DeleteHistory.class).hasSize(1);
    }

}
