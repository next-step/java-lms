package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {

    private Answer A1;
    private Answer A2;
    private Question Q1;

    private DeleteHistories deleteHistories;
    @BeforeEach
    void setUp() {
        deleteHistories = new DeleteHistories();
        this.Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        this.A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        this.A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
    }

    @DisplayName("Question을 add 하면 question에 대한 DeleteHistory를 만들어 리스트에 추가한다.")
    @Test
    void when_addQuestionToDeleteHistories_Expects_containsExactlySameDeleteHistory() {
        deleteHistories.add(DeleteHistory.createQuestion(Q1.getId(), Q1.getWriter()));
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter());

        assertThat(deleteHistories.getDeleteHistories())
                .containsExactly(deleteHistory)
                .hasSize(1);
    }

    @DisplayName("Answer를 add 하면 question에 대한 DeleteHistory를 만들어 리스트에 추가한다.")
    @Test
    void when_addAnswersToDeleteHistories_Expects_containsExactlySameDeleteHistory() {
        deleteHistories.add(DeleteHistory.createAnswer(A1.getId(), A1.getWriter()));
        deleteHistories.add(DeleteHistory.createAnswer(A2.getId(), A2.getWriter()));

        DeleteHistory deleteHistoryA1 = new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter());
        DeleteHistory deleteHistoryA2 = new DeleteHistory(ContentType.ANSWER, A2.getId(), A2.getWriter());

        assertThat(deleteHistories.getDeleteHistories())
                .containsExactly(deleteHistoryA1, deleteHistoryA2)
                .hasSize(2);
    }
}