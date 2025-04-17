package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    private Answer answer;

    @BeforeEach
    void setUp() {
        Question question = new Question(JAVAJIGI, "title1", "contents1");
        answer = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
    }

    @Test
    @DisplayName("정상적으로 답변 삭제에 성공한다")
    void delete_normal_case() {
        answer.delete(NsUserTest.JAVAJIGI);
        assertTrue(answer.isDeleted());
    }

    @Test
    @DisplayName("권한 없는 사용자가 답변 삭제를 요청하면 예외를 던진다")
    void cannot_delete_answer_another_user() {
        assertThatThrownBy(() -> answer.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
