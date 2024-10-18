package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @Test
    @DisplayName("답변을 추가한다.")
    void 답변_추가() {
        Answers answers = new Answers(List.of(A1));
        answers.addAnswer(A2);
        assertThat(answers).isEqualTo(new Answers(List.of(A1, A2)));
    }

    @Test
    @DisplayName("Qna를 삭제하면 그 하위에 있는 답변을 DeleteHistory에 담아 반환한다.")
    void 답변_삭제_리스트_반환() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new Answers(List.of(A1, A1)).deleteAnswers(NsUserTest.JAVAJIGI);
        assertThat(deleteHistories).hasSize(2);
    }

    @Test
    @DisplayName("QnA를 삭제하면 그 하위에 있는 답변을 모두 삭제처리 한다.")
    void 답변_삭제() throws CannotDeleteException {
        new Answers(List.of(A1)).deleteAnswers(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("다른 사람이 답변을 썼으면, CannotDeleteException 에러를 반환시킨다.")
    void 답변일치_에러() {
        assertThatThrownBy(() -> new Answers(List.of(A1)).validateOwner(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
