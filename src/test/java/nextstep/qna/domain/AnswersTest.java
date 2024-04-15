package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @Test
    @DisplayName("Answers 삭제 성공 테스트")
    void deleteAnswersSuccessTest() throws Exception {
        Answer answer = AnswerTest.A1;
        Answers answers = new Answers(List.of(answer));

        List<DeleteHistory> deleteHistoryList = answers.delete(NsUserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistoryList).hasSize(1);
    }

    @Test
    @DisplayName("Answers 삭제 실패 테스트 - 다른 사람이 쓴 답변")
    void deleteAnswersFailForOtherUserAnswerTest() {
        Answers answers = new Answers(List.of(AnswerTest.A2));

        assertThatThrownBy(() -> {
            answers.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
