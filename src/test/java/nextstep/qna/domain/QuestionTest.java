package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 삭제할_수_없는_유저일_때() {
        assertThatThrownBy(()->
                Q1.delete(NsUserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");

    }

    @Test
    void 질문_삭제_성공() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void 삭제_히스토리_생성() {
        Q1.addAnswer(AnswerTest.A1);

        assertThat(Q1.createDeleteHistories()).hasSize(2);
    }
}
