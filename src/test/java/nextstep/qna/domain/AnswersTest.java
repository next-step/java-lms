package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    private static final List<Answer> answers1 = new ArrayList<>();
    private static final List<Answer> answers2 = new ArrayList<>();

    @Test
    void create() {
        assertThat(new Answers(answers1)).isEqualTo(new Answers(answers1));
    }

    @Test
    void add() {
        Answers answers = new Answers(answers1);
        answers.add(AnswerTest.A1);
        assertThat(answers).isEqualTo(new Answers(List.of(AnswerTest.A1)));
    }

    @Test
    void delete_실패_다른_사람_답변() {
        assertThatThrownBy(
                () -> AnswerTest.A1.checkOtherAnswer(NsUserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void deleteHistory_추가() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers2.add(AnswerTest.A1);
        new Answers(answers2).addDeleteHistories(deleteHistories);
        assertThat(deleteHistories)
                .containsExactly(
                        new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now())
                );
    }
}
