package nextstep.qna.domain;

import static nextstep.qna.CommonTestFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;

public class AnswerTest {

    @Test
    void 답변자_질문자_일치_실패() {
        assertNotEquals(A1.getWriter(), A2.getWriter());
    }

    @Test
    void 일치_실패할경우_삭제시_예외발생() {
        assertAll(
            () -> assertThatThrownBy(
                () -> A1.deleteBy(A2.getWriter())
            ).isInstanceOf(CannotDeleteException.class),
            () -> assertFalse(A1.isDeleted())
        );
    }

    @Test
    void 일치_성공할경우_삭제() throws CannotDeleteException {
        A1.deleteBy(A1.getWriter());
        assertTrue(A1.isDeleted());
    }
}
