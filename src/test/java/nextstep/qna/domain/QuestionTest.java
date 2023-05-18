package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_질문을_삭제할_권한이_없습니다_예외() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI, 1))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void delete_상태값_변경_확인() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI, 1);
        assertTrue(Q1.isDeleted());
    }

    @Test
    void delete_반환값_확인() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI, 1);
        assertThat(deleteHistories.size()).isEqualTo(1);
    }

}
