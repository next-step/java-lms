package nextstep.qna.domain;

import nextstep.qna.AnswerFixtures;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AnswerTest {

    @DisplayName("soft delete 테스트")
    @Test
    void delete() {

        Answer sut = AnswerFixtures.A1;
        sut.delete(NsUserFixtures.JAVAJIGI);

        assertThat(sut.isDeleted()).isTrue();
    }

    @DisplayName("답변 작성자만 삭제 가능하다")
    @Test
    void deleteFailTest() {
        Answer sut = AnswerFixtures.A1;

        assertThatThrownBy(() -> sut.delete(NsUserFixtures.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("answer 삭제 히스토리 타입 테스트")
    @Test
    void deleteReturnDeleteHistoryTest() {
        Answer answer = AnswerFixtures.A1;
        DeleteHistory deleteHistory = answer.delete(NsUserFixtures.JAVAJIGI);

        assertThat(deleteHistory.isAnswer()).isTrue();
    }
}
