package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DeleteHistoriesTest {

    private Question Q1;
    private Answer A1;
    private Answer A2;

    private DeleteHistories deleteHistories;

    @BeforeEach
    void setUp() {
        this.deleteHistories = new DeleteHistories();
        this.Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        this.A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        this.A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
    }

    @DisplayName("Question을 추가했을 경우 Question에 대한 DeleteHistory를 만들어 리스트에 추가한다.")
    @Test
    void addQuestion_CreateDeleteHistory_And_AddDeleteHistorylist() {
        deleteHistories.add(Q1);
        DeleteHistory deleteHistoryQ1 =
                new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now());

        Assertions.assertThat(deleteHistories.getDeleteHistories())
                .containsExactly(deleteHistoryQ1)
                .hasSize(1);
    }

    @DisplayName("Answer를 추가했을 경우 Add에 대한 DeleteHistory를 만들어 리스트에 추가한다.")
    @Test
    void addQuestion_CreateDeleteHistory_And_AddDeleteHistorylist() {
        deleteHistories.add(A1);
        DeleteHistory deleteHistoryA1 =
                new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now());
        DeleteHistory deleteHistoryA2 =
                new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now());

        Assertions.assertThat(deleteHistories.getDeleteHistories())
                .containsExactly(deleteHistoryA1, deleteHistoryA2)
                .hasSize(2);
    }

}
