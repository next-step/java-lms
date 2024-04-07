package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.question.Question;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문 삭제는")
    @Nested
    class Describe_delete {

        @DisplayName("soft-delete 처리 후 질문과 답변의 삭제 이력 목록을 반환한다.")
        @Test
        void it_returns_deleted_histories() {

        }

        @DisplayName("다른 사람이 작성한 질문은 삭제할 수 없다.")
        @Test
        void it_throws_cannot_delete_exception_when_others_question() throws Exception {
            assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI, LocalDateTime.now()))
                    .isInstanceOf(CannotDeleteException.class);
        }

        @DisplayName("다른 사람이 작성한 답변이 있는 경우 삭제할 수 없다.")
        @Test
        void it_throws_cannot_delete_exception_when_others_answer_exist() throws Exception {
            Q1.addAnswer(AnswerTest.A2);
            assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI, LocalDateTime.now()))
                    .isInstanceOf(CannotDeleteException.class);
        }
    }

}
