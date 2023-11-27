package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("삭제유효성검사 / 로그인사용자와 질문한사람이 같다 / 통과")
    void deleteSameLoginQuestionUser() throws CannotDeleteException {
        // when then
        Q1.validateOwner(NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("삭제유효성검사 / 로그인사용자와 질문한사람이 다르다 / CannotDeleteException")
    void deleteDiffLoginQuestionUser() {
        // when then
        Assertions.assertThatThrownBy(() -> Q1.validateOwner(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
