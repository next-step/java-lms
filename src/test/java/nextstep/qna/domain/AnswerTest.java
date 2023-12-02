package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.fixture.NsUserFixture.*;
import static org.assertj.core.api.Assertions.*;

class AnswerTest {

    private static final String TEST_CONTENTS = "test_contents";

    @DisplayName("답변은 답변 소유자만 삭제할 수 있다.")
    @Test
    void if_user_is_answer_owner_then_delete_answer() {
        Times times = new Times(LocalDateTime.now(), null);
        AnswerInformation information = new AnswerInformation(TEST_CONTENTS, JAVAJIGI, false, times);
        Answer answer = new Answer(1L, information);
        NsUser loginUser = JAVAJIGI;

        Answer deletedAnswer = answer.delete(loginUser);
        boolean actual = deletedAnswer.isDeleted();

        assertThat(actual).isTrue();
    }

    @DisplayName("답변 소유자가 아닐 경우 예외가 발생한다.")
    @Test
    void if_user_is_not_answer_owner_then_throw_exception() {
        Times times = new Times(LocalDateTime.now(), null);
        AnswerInformation information = new AnswerInformation(TEST_CONTENTS, JAVAJIGI, false, times);
        Answer answer = new Answer(1L, information);
        NsUser loginUser = SANJIGI;

        assertThatThrownBy(() -> answer.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("삭제된 답변은 DeleteHistory으로 이력을 남긴다.")
    @Test
    void removed_answer_creates_DeleteHistory_object() {
        Times times = new Times(LocalDateTime.now(), null);
        AnswerInformation information = new AnswerInformation(TEST_CONTENTS, JAVAJIGI, false, times);
        Answer answer = new Answer(1L, information);
        NsUser loginUser = JAVAJIGI;
        Answer deletedAnswer = answer.delete(loginUser);
        DeletedHistory expected = new DeletedHistory(ContentType.ANSWER,
                                                     deletedAnswer.getId(),
                                                     deletedAnswer.getDeletedBy());

        DeletedHistory actual = deletedAnswer.build();

        assertThat(actual).isEqualTo(expected);
    }
}