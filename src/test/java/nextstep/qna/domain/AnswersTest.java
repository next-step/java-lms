package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    private Answers answers;

    @Test
    @DisplayName("로그인한 유저와 답변 작성자가 같은 경우 답변 삭제에 성공한다.")
    public void delete_answer_from_equal_user() throws Exception {
        answers = new Answers(Arrays.asList(AnswerTest.A3, AnswerTest.A4));

        List<DeleteHistory> deleteHistories = answers.delete(NsUserTest.SANJIGI);

        assertThat(AnswerTest.A3.isOwner(NsUserTest.SANJIGI)).isTrue();
        assertThat(AnswerTest.A4.isOwner(NsUserTest.SANJIGI)).isTrue();

        assertThat(deleteHistories.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("로그인한 유저와 답변 작성자가 다른 경우 답변 삭제에 실패한다.")
    public void delete_answer_from_not_equal_user() {
        answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
