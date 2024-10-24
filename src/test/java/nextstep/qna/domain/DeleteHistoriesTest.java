package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeleteHistoriesTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    @Test
    @DisplayName("성공 - add 메서드가 삭제내역을 추가한다.")
    void addTest() {
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, 1L, Q1.getWriter());
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(deleteHistory);

        assertThat(deleteHistories.getDeleteHistories()).hasSize(1);
        assertThat(deleteHistories.getDeleteHistories()).containsExactly(deleteHistory);
    }

    @Test
    @DisplayName("실패 - getDeleteHistories 메서드의 반환된 List를 수정했을 때 예외가 발생한다.")
    void throwExceptionWhenModifyingReturnedDeleteHistoryList() {
        DeleteHistories deleteHistories = new DeleteHistories();
        assertThatThrownBy(() -> deleteHistories.getDeleteHistories().add(
                new DeleteHistory(ContentType.QUESTION, 1L, Q1.getWriter())))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("성공 - DeleteHistories를 생성 시 Answers 요소 크기 만큼 값에 포함된다.")
    void deleteHistoriesInitTest() {
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.createQuestion(NsUserTest.JAVAJIGI), "답변1");
        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.createQuestion(NsUserTest.JAVAJIGI), "답변1");
        Answers answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);

        DeleteHistories deleteHistories = new DeleteHistories(answers);
        assertThat(deleteHistories.getDeleteHistories())
                .hasSize(2);
    }
}