package nextstep.qna.domain;

import java.util.List;
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
    @DisplayName("작성자가 모든 답변을 작성했으면 삭제가 가능하다.")
    void deleteBy_allOwner_success() throws CannotDeleteException {
        Q1.addAnswer(new Answer(NsUserTest.JAVAJIGI, Q1, "answer1"));
        Q1.addAnswer(new Answer(NsUserTest.JAVAJIGI, Q1, "answer2"));

        List<DeleteHistory> histories = Q1.deleteBy(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(histories).hasSize(3);
    }

    @Test
    @DisplayName("질문 작성자가 아니면 CannotDeleteException 예외가 발생한다.")
    void deleteBy_notOwner_fail() {
        assertThatThrownBy(() -> Q2.deleteBy(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("답변 중 하나라도 다른 사람이 썼으면 CannotDeleteException 예외가 발생한다.")
    void deleteBy_otherAnswer_fail() {
        Q2.addAnswer(new Answer(NsUserTest.JAVAJIGI, Q2, "answer1"));

        assertThatThrownBy(() -> Q2.deleteBy(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

}

