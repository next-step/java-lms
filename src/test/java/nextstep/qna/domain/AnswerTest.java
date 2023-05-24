package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.fixture.QuestionFixture;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswerTest {
    @Test
    @DisplayName("로그인 사용자와 답변 작성자가 다른 경우 삭제할 수 없다.")
    void validateDelete() {
        Answer answer = QuestionFixture.createAnswer(NsUserTest.SANJIGI);
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answer.delete(NsUserTest.JAVAJIGI));
    }
}
