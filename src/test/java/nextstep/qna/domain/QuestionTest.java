package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.AnswerTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 삭제 불가능")
    @Test
    void 질문_삭제_불가능() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .withMessageMatching("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능")
    @Test
    void 질문_삭제_가능() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        Q2.delete(NsUserTest.SANJIGI);

        Assertions.assertAll(
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(Q2.isDeleted()).isTrue()
        );

    }

    @DisplayName("질문자와 답변자가 다른 경우, 답변 삭제 불가능")
    @Test
    void 질문_삭제_답변삭제불가능() {
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
                .withMessageMatching(Question.DELETE_ANSWERS_AUTHORITY);
    }

    @DisplayName("질문자와 답변글의 모든 답변자가 같은 경우, 삭제 가능")
    @Test
    void 질문_삭제_답변삭제가능() throws CannotDeleteException {
        Q1.addAnswer(A1);
        Q1.addAnswer(A3);

        Q1.delete(NsUserTest.JAVAJIGI);

        Assertions.assertAll(
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(A1.isDeleted()).isTrue(),
                () -> assertThat(A3.isDeleted()).isTrue()
        );
    }
}



