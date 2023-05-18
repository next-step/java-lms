package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문 삭제 요청을 나타내는 필드 표시")
    void question_delete_mark() {
        Q1.delete();

        assertThat(Q1.isDeleted())
                .isTrue();
    }

    @Test
    @DisplayName("질문을 삭제할 권한이 없으면 exception")
    void check_authority_to_delete() {
        assertThatThrownBy(() -> Q1.checkAuthorityToDelete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
