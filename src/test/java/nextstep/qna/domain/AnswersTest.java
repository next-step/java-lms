package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class AnswersTest {

    @Test
    void delete() {
        assertThat(new Answers(List.of(A1)).delete(NsUserTest.JAVAJIGI))
            .hasSize(1);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> new Answers(List.of(A2)).delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void add() {
        Answers answers = new Answers();
        answers.add(A1);
        assertThat(answers).isEqualTo(new Answers(List.of(A1)));
    }

}
