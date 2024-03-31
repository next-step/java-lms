package nextstep.qna.domain;

import static nextstep.qna.domain.TestFixtures.FIXED_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    public static final Answer A1 = new Answer(
        NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1",
        FIXED_DATE_TIME
    );
    public static final Answer A2 = new Answer(
        NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2",
        FIXED_DATE_TIME
    );

    @Test
    @DisplayName("작성자 본인인 경우 성공적으로 삭제된다")
    void delete() throws CannotDeleteException{
        // when
        DeleteHistory deleteHistory = A1.delete(NsUserTest.JAVAJIGI, FIXED_DATE_TIME);

        // then
        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistory.getContentId()).isEqualTo(A1.getId());
        assertThat(deleteHistory.getContentType()).isEqualTo(ContentType.ANSWER);
        assertThat(deleteHistory.getDeletedBy()).isEqualTo(NsUserTest.JAVAJIGI);
        assertThat(deleteHistory.getCreatedDate()).isEqualTo(FIXED_DATE_TIME);
    }

    @Test
    @DisplayName("작성자가 아닌데 삭제하려하면 예외가 발생한다")
    void delete_fail_for_not_owner() {
        assertThatThrownBy(() -> A2.delete(NsUserTest.JAVAJIGI, FIXED_DATE_TIME))
            .isInstanceOf(CannotDeleteException.class);
    }
}
