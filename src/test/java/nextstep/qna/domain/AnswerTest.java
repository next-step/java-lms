package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.fixture.AnswerFixture;
import nextstep.users.domain.NsUserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class AnswerTest {


    @Test
    @DisplayName("답변을 삭제할 경우 답변이 삭제된다.")
    void userSameAnswerDeleteSuccess () {
        AnswerFixture.A1.delete(NsUserFixture.JAVAJIGI);

        assertThat(AnswerFixture.A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("다른사람이 답변을 단 글을 삭제할 경우 예외가 발생한다.")
    void userDifferentAnswerDeleteSuccess() {
        assertThatThrownBy(() -> AnswerFixture.A1.delete(NsUserFixture.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);

    }

}
