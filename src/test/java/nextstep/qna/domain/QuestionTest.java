package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    public static final Question Q1 = new Question(JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_질문을_삭제할_권한이_없습니다_예외() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void delete_상태값_변경_확인() throws CannotDeleteException {
        Q1.delete(JAVAJIGI);
        assertTrue(Q1.isDeleted());
    }

    @Test
    void delete_반환값_확인() throws CannotDeleteException {
        DeleteHistories deleteHistories = Q1.delete(JAVAJIGI);
        assertThat(deleteHistories.value().size()).isEqualTo(1);
    }

    @Test
    void delete_answer를_가진_상태의_question() throws CannotDeleteException {
        //given
        Q1.addAnswer(A1);

        //when
        Q1.delete(JAVAJIGI);

        //then
        assertTrue(Q1.isDeleted());
        assertTrue(A1.isDeleted());
    }
}
