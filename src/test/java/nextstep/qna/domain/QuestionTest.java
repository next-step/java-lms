package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.AnswerTest.A1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("삭제유효성검사 / 로그인사용자와 질문한사람이 같다 / 통과")
    void deleteSameLoginQuestionUser() {
        // when then
        Q1.validateDeletable(NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("삭제유효성검사 / 로그인사용자와 질문한사람이 다르다 / CannotDeleteException")
    void deleteDiffLoginQuestionUser() {
        // when then
        assertThatThrownBy(() -> Q1.validateDeletable(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 / Question과 Answer을 모두 삭제한다 / softDelete")
    void delete() {
        // given
        Q1.addAnswer(A1);

        // when
        Q1.delete();

        // then
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isTrue();
    }
}
