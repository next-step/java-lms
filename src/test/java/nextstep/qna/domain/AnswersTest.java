package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @DisplayName("Answers 삭제 성공")
    @Test
    void deleteAll_success() {
        // given
        Answers answers = new Answers();
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3"));

        // when
        List<DeleteHistory> deleteHistories = answers.deleteAll(NsUserTest.JAVAJIGI);

        // then
        assertThat(answers.getValues()).hasSize(3);
        assertThat(answers.getValues()).extracting("deleted").containsOnly(true);

        assertThat(deleteHistories).hasSize(3);
        assertThat(deleteHistories).extracting("contentId").containsOnly(answers.getValues().stream().map(Answer::getId).toArray());
        assertThat(deleteHistories).extracting("contentType").containsOnly(ContentType.ANSWER);
        assertThat(deleteHistories).extracting("deletedBy").containsOnly(NsUserTest.JAVAJIGI);
    }

    @DisplayName("Answers 삭제 실패 - 다른 사용자")
    @Test
    void deleteAll_owner_validate_fail() {
        // given
        Answers answers = new Answers();
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3"));


        assertThat(answers.getValues()).hasSize(3);
        assertThatThrownBy(() -> answers.deleteAll(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
