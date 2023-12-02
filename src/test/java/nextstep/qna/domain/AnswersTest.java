package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.fixture.NsUserFixture.JAVAJIGI;
import static org.assertj.core.api.Assertions.*;

class AnswersTest {

    private static final String TEST_CONTENTS = "test_contents";

    @DisplayName("삭제된 답변과 삭제 목록의 수는 동일하다.")
    @Test
    void deleted_answers_and_deletedHistories_number_are_same() {
        Times times = new Times(LocalDateTime.now(), null);
        AnswerInformation information = new AnswerInformation(TEST_CONTENTS, JAVAJIGI, false, times);
        Answer answer1 = new Answer(1L, information);
        Answer answer2 = new Answer(2L, information);
        Answers answers = new Answers(answer1, answer2);
        NsUser loginUser = JAVAJIGI;

        Answers deletedAnswers = answers.delete(loginUser);
        List<DeletedHistory> actual = deletedAnswers.buildHistories();

        assertThat(actual).hasSize(2);
    }
}
