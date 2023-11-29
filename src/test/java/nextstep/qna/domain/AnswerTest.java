package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.fixture.AnswerFixture.*;
import static nextstep.fixture.NsUserFixture.*;
import static nextstep.fixture.QuestionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    private Answer answer;

    private NsUser loginUser;

    @BeforeEach
    void setUp() {
        answer = new Answer(A1.getId(), A1.getWriter(), Q1, A1.getContents());
        loginUser = A1.getWriter();
    }

    @DisplayName("다른 사람이 쓴 답변이 있다면 예외가 발생한다..")
    @Test
    void if_user_is_not_same_as_login_user_then_can_not_delete_question() {
        assertThatThrownBy(() -> answer.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 데이터는 삭제 후 soft delete 처리가 된다.")
    @Test
    void set_delete_true_when_answer_was_deleted() {
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER,
                                                   A1.getId(),
                                                   A1.getWriter(),
                                                   LocalDateTime.now());

        DeleteHistory actual = answer.delete(loginUser);

        assertThat(answer.isDeleted()).isTrue();
        assertThat(actual).isEqualTo(expected);
    }
}
