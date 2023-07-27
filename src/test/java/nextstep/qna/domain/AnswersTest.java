package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswersTest {

    @Test
    void add_테스트() {
        Answers answers = new Answers();

        answers.addAnswer(A1);

        answers.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void delete_성공_같은_유저() throws CannotDeleteException {
        Answers answers = new Answers(List.of(A1));
        List<DeleteHistory> deleteHistories = answers.delete(NsUserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistories.get(0)).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }

    @Test
    void delete_실패_다른_유저() {
        Answers answers = new Answers(List.of(A1));

        assertThatThrownBy(() -> answers.delete(NsUserTest.SANJIGI)).isInstanceOf(RuntimeException.class);
    }
}
