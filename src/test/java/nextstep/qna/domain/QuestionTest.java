package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("삭제할 권한이 있다면 질문이 삭제됩니다.")
    @Test
    void canDeleteIfYouHavePermission() throws CannotDeleteException {
        NsUser writer = Q1.getWriter();
        assertThatNoException().isThrownBy(() -> Q1.deleteQuestionAndRelatedAnswer(writer, LocalDateTime.now()));
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("삭제할 권한이 없다면 질문을 삭제할 수 없습니다.")
    @Test
    void canNotDeleteIfYouDontHavePermission() throws CannotDeleteException {
        NsUser writer = Q1.getWriter();
        assertThatThrownBy(() -> Q2.deleteQuestionAndRelatedAnswer(writer, LocalDateTime.now())).isInstanceOf(CannotDeleteException.class);
        assertThat(Q2.isDeleted()).isFalse();
    }

}
