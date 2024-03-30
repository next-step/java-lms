package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("Question이 자신이 작성한 것이 아니면 삭제할 수 없다.")
    void isMyQuestion() {
        NsUser writer = NsUserTest.SANJIGI;
        assertThatThrownBy(() -> Q1.validateDeletable(writer)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("Answer가 본인이 작성하지 않으면 삭제할 수 없다.")
    void isMyAnswer() {
        NsUser writer = NsUserTest.SANJIGI;
        Q1.addAnswer(new Answer(writer, Q1, "test"));
        assertThatThrownBy(() -> Q1.validateDeletable(writer)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제할 수 있는 질문인지 확인한다.")
    void isDeletable(){
        NsUser writer = NsUserTest.SANJIGI;
        assertThatNoException().isThrownBy(() -> Q2.validateDeletable(writer));
    }

    @Test
    @DisplayName("deleted를 true로 만들고 삭제 이력 목록을 반환")
    void delete() {
        List<DeleteHistory> deleteHistories = Q1.delete();
        assertThat(deleteHistories).isNotEmpty();
        assertThat(Q1.isDeleted()).isTrue();
    }
}
