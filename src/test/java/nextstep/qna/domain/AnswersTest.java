package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    @Test
    @DisplayName("모든 답변을 삭제한다.")
    void testDeleteAll() throws CannotDeleteException {
        //given
        final Answer tempA1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Temp Answers Contents1");
        final Answer tempA2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Temp Answers Contents2");

        final Answers answers = new Answers(List.of(tempA1, tempA2));

        //when
        final List<DeleteHistory> deleteHistories = answers.deleteAll(NsUserTest.JAVAJIGI);

        final boolean isDeletedTempA1 = tempA1.isDeleted();
        final boolean isDeletedTempA2 = tempA2.isDeleted();
        final int sizeAfterDelete = deleteHistories.size();

        //then
        assertThat(isDeletedTempA1).isTrue();
        assertThat(isDeletedTempA2).isTrue();
        assertThat(sizeAfterDelete).isEqualTo(2);
    }
}
