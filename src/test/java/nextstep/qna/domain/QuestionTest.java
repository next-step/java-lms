package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("답변 삭제 시 주인이 아닌 경우 CannotDeleteException 예외를 던진다.")
    void 답변_삭제_예외_주인아닐경우() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변을 삭제하면 DeleteHistory에 담아 반환한다.")
    void 답변_삭제_리스트_반환() throws CannotDeleteException {
        DeleteHistory deleteHistory = Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
    }


    @Test
    @DisplayName("질문을 삭제한다.")
    void 질문_삭제() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        Assertions.assertThat(Q1.isDeleted()).isTrue();
    }
}
