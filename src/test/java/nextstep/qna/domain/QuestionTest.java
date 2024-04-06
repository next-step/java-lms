package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("로그인한 유저와 질문 작성자가 같은 경우 질문 삭제에 성공한다.")
    public void delete_question_from_equal_user () throws Exception {
        Q2.addAnswer(AnswerTest.A3);
        Q2.addAnswer(AnswerTest.A4);

        assertThat(Q2.isDeleted()).isFalse();
        assertThat(AnswerTest.A3.isDeleted()).isFalse();
        assertThat(AnswerTest.A4.isDeleted()).isFalse();

        List<DeleteHistory> deleteHistories = Q2.delete(NsUserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
        assertThat(AnswerTest.A3.isDeleted()).isTrue();
        assertThat(AnswerTest.A4.isDeleted()).isTrue();

        assertEquals(3, deleteHistories.size());
    }

    @Test
    @DisplayName("로그인한 유저와 질문 작성자가 다른 경우 질문 삭제에 실패한다.")
    public void delete_question_from_not_equal_user () {
        assertThat(Q1.isDeleted()).isFalse();
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
