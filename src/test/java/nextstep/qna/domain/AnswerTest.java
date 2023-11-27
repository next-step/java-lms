package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "testAnswer");
    }

    @DisplayName("Answer 도메인 객체 삭제 성공")
    @Test
    void delete_success() {

        DeleteHistory deleteHistory = answer.delete(NsUserTest.JAVAJIGI);
        // then
        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistory.getContentId()).isEqualTo(answer.getId());
        assertThat(deleteHistory.getContentType()).isEqualTo(ContentType.ANSWER);
        assertThat(deleteHistory.getDeletedBy()).isEqualTo(NsUserTest.JAVAJIGI);
    }

    @DisplayName("Answer 도메인 객체 삭제 실패 - 다른 사용자")
    @Test
    void delete_owner_validate_fail() {
        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThat(answer.isDeleted()).isFalse();
    }
}
