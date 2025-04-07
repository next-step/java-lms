package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswersTest {
    public static final Answers ANSWERS = new Answers(List.of(A2, A3));

    @Test
    void shouldNotAllowDelete_WhenUserIsNotOwner(){
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> ANSWERS.delete(NsUserTest.JAVAJIGI))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void shouldDelete_WhenUserIsOwner() throws CannotDeleteException {
        ANSWERS.delete(NsUserTest.SANJIGI);
        assertThat(ANSWERS.isDeleted()).isTrue();
    }
}
