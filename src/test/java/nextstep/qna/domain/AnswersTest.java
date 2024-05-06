package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswersTest {

    Answers sameOwnerAnswers;
    Answers diffOwnerAnswers;
    NsUser logUser;

    @BeforeEach
    void init() {
        logUser = NsUserTest.JAVAJIGI;
        sameOwnerAnswers = new Answers(AnswerTest.A1, AnswerTest.A1);
        diffOwnerAnswers = new Answers(AnswerTest.A1, AnswerTest.A2);
    }

    @Test
    @DisplayName("댓글들이 모두 로그인 유저와 동일하면 true를 반환한다.")
    void ANSWERS_USERS_SAME_WITH_LOG_USER_THEN_TRUE() {
        assertThat(sameOwnerAnswers.isOwners(logUser)).isEqualTo(true);
    }

    @Test
    @DisplayName("댓글들 중 하나라도 로그인 유저와 같지 않다면 false를 반환한다.")
    void ALL_ANSWERS_USERS_NOT_SAME_WITH_LOG_USER_THEN_FALSE() {
        assertThat(diffOwnerAnswers.isOwners(logUser)).isEqualTo(false);
    }

    @Test
    @DisplayName("댓글 중 로그인 유저와 같지 않은 유저가 작성한 댓글이 있으면 삭제가 불가능하다.")
    void CANNOT_DELETE_IF_EXISTS_NOT_SAME_ANSWER_USER_WITH_LOG_USER() {
        Assertions.assertThatThrownBy(() -> diffOwnerAnswers.delete(logUser))
            .isInstanceOf(CannotDeleteException.class);
    }

}
