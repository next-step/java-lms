package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    @Test
    @DisplayName("답변 목록 모두 동일한 사용자가 작성하지 않았다면 false 를 반환한다.")
    void shouldReturnFalseWhenAnswersAreNotAllFromSameUser() {
        final Answers answers = new Answers(
            List.of(
                new Answer(NsUserTest.SANGHYUN, QuestionTest.Q3, "답변1"),
                new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q3, "답변2")
            )
        );

        assertThat(answers.allMatch(NsUserTest.SANGHYUN)).isFalse();
    }

    @Test
    @DisplayName("답변 목록 모두 동일한 사용자가 작성했다면 true 를 반환한다.")
    void shouldReturnTrueWhenAllAnswersAreFromSameUser() {
        final Answers answers = new Answers(
            List.of(
                new Answer(NsUserTest.SANGHYUN, QuestionTest.Q3, "답변1"),
                new Answer(NsUserTest.SANGHYUN, QuestionTest.Q3, "답변2")
            )
        );

        assertThat(answers.allMatch(NsUserTest.SANGHYUN)).isTrue();
    }

    @Test
    @DisplayName("답변 목록 모두 동일한 사용자가 작성했다면 DeleteHistory 목록을 반환한다.")
    void shouldReturnDeleteHistoryListWhenAllAnswersAreFromSameUser() {
        final Answer answer1 = new Answer(NsUserTest.SANGHYUN, QuestionTest.Q3, "답변1");
        final Answer answer2 = new Answer(NsUserTest.SANGHYUN, QuestionTest.Q3, "답변2");
        final Answers answers = new Answers(List.of(answer1, answer2));
        final List<DeleteHistory> resultDeleteHistories = List.of(
            new DeleteHistory(ContentType.ANSWER, answer1.getId(), NsUserTest.SANGHYUN, LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, answer2.getId(), NsUserTest.SANGHYUN, LocalDateTime.now())
        );

        assertThat(answers.deleteAll(NsUserTest.SANGHYUN))
            .isEqualTo(resultDeleteHistories);
    }
}