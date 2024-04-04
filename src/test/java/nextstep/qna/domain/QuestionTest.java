package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문이 삭제가 되지 않았을 때 이력으로 남길려고 하면 예외를 발생시킨다")
    @Test
    void to_histories_exception() {
        assertThatThrownBy(() -> Q1.toHistories())
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문이 삭제가 되지 않았습니다");
    }

    @DisplayName("질문 Q1을 toHistories()메서드를 통해 이력으로 만든다")
    @Test
    void to_histories() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        List<DeleteHistory> histories = Q1.toHistories();
        assertThat(histories.size()).isEqualTo(1);
    }

    @DisplayName("질문 Q1이 Q1을 작성한 사람을 삭제한다")
    @Test
    void delete_자바지기() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문 Q1이 Q1을 작성하지 않은 사람을 삭제할려는 경우 예외를 던진다")
    @Test
    void delete_exception() {
        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

}
