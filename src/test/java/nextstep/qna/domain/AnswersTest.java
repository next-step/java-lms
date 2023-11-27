package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    public static final Answers A1 = new Answers(List.of(AnswerTest.A1));
    public static final Answers A1_A2 = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));

    @Test
    @DisplayName("삭제유효성검사 / 로그인사용자와 모든 질문등록자가 같다 / 통과")
    void deleteSameLoginAnswerUser() {
        // when then
        A1.validateAllOwner(NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("삭제유효성검사 / 로그인사용자와 질문등록자가 다르다 / 통과")
    void deleteDiffLoginAnswerUser() {
        // when then
        assertThatThrownBy(() -> A1_A2.validateAllOwner(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}