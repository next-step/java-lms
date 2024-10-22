package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 작성자와 로그인 유저가 다르면 삭제할 수 없다")
    @Test
    void checkDeletePermission() throws CannotDeleteException {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("답변 삭제 후, 삭제 이력을 반환한다")
    @Test
    void delete() throws CannotDeleteException {
        DeleteHistory history = A1.delete(NsUserTest.JAVAJIGI);

        assertThat(history).extracting("contentType", "deletedBy")
                .containsExactly(ContentType.ANSWER, NsUserTest.JAVAJIGI);
    }

}
