package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.ANSWER;
import static org.assertj.core.api.Assertions.assertThat;

import nextstep.dummy.answer.AnswerDummy;
import nextstep.dummy.answer.NsUserDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    private Answer a_user_answer;

    @BeforeEach
    void init() {
        a_user_answer = new AnswerDummy().a_answer;
    }

    @Test
    @DisplayName("답변내역을 삭제한다.")
    void deleteTest() {
        a_user_answer.deleteSelf();
        assertThat(a_user_answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제가 되지 않은 답변에 대해 히스토리를 반환받을 경우 빈 객체를 리턴한다.")
    void deleteHistoryEmptyTest() {
        assertThat(a_user_answer.deleteHistory().isEmpty()).isTrue();
    }

    @Test
    @DisplayName("삭제된 답변에 대해 삭제 내역을 리턴한다.")
    void deleteHistoryTest() {
        a_user_answer.deleteSelf();
        assertThat(a_user_answer.deleteHistory())
                .isEqualTo(
                        new DeleteHistory(ANSWER, null, new NsUserDummy().a_user));
    }
}
