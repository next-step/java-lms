package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeleteHistoriesTest {
    private DeleteHistories deleteHistoriesOne;
    private DeleteHistories deleteHistoriesTwo;
    private DeleteHistory deleteHistory;

    @BeforeEach
    public void beforeEach() {
        Question question = Question.of(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = Answer.of(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answers answers = Answers.of(List.of(answer));
        question.loadAnswers(answers);
        deleteHistory = DeleteHistory.of(ContentType.ANSWER, answer.getId(), answer.getWriter());

        deleteHistoriesOne = DeleteHistories.create();
        deleteHistoriesTwo = DeleteHistories.create();
        deleteHistoriesOne.add(deleteHistory);
    }

    @Test
    @DisplayName("DeleteHistories 객체가 잘 생성되는지 확인")
    void 객체가_정상적으로_생성되는지_확인() {

        assertAll(
                () -> assertThat(deleteHistoriesOne).isNotNull(),
                () -> assertThat(deleteHistoriesOne.getDeleteHistories()).hasSize(1),
                () -> assertThat(deleteHistoriesOne.getDeleteHistories()).containsExactly(deleteHistory)
        );
    }

    @DisplayName("DeleteHistory 객체가 한건이 잘 추가되는지 확인")
    @Test
    void DeleteHistory_객체_한건이_정상적으로_추가되는지_확인() {
        deleteHistoriesTwo.add(deleteHistory);
        assertAll(
                () -> assertThat(deleteHistoriesOne).isNotNull(),
                () -> assertThat(deleteHistoriesTwo.getDeleteHistories()).hasSize(1),
                () -> assertThat(deleteHistoriesTwo.getDeleteHistories()).containsExactly(deleteHistory),
                () -> assertThat(deleteHistoriesTwo).isEqualTo(deleteHistoriesOne)
        );
    }

    @DisplayName("DeleteHistory 객체끼리 합성이 되는지 확인")
    @Test
    void DeleteHistory_객체끼리_합성이_되는지_확인() {
        deleteHistoriesTwo.concat(deleteHistoriesOne);
        assertAll(
                () -> assertThat(deleteHistoriesOne).isNotNull(),
                () -> assertThat(deleteHistoriesTwo.getDeleteHistories()).hasSize(1),
                () -> assertThat(deleteHistoriesTwo.getDeleteHistories()).containsExactly(deleteHistory),
                () -> assertThat(deleteHistoriesTwo).isEqualTo(deleteHistoriesOne)
        );
    }
}