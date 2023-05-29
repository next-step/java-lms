package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

public class DeleteHistoriesTest {
    @DisplayName("두 개의 비어있는 서로 다른 new DeleteHistories는 동일하게 취급된다.")
    @Test
    void ShouldTwoHistoriesBeEqual_whenEmptyHistories() {
        DeleteHistories deleteHistories1 = new DeleteHistories();
        DeleteHistories deleteHistories2 = new DeleteHistories();

        assertThat(deleteHistories1.equals(deleteHistories2)).isTrue();
    }

    @DisplayName("동일한 History를 포함하는 두 개의 서로 다른 DeleteHistories는 동일하게 취급된다.")
    @Test
    void ShouldTwoHistoriesBeEqual_whenIncludingHistoryIsSame() {
        DeleteHistories deleteHistories1 = new DeleteHistories();
        DeleteHistories deleteHistories2 = new DeleteHistories();
        deleteHistories1.addHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI);
        deleteHistories2.addHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI);

        assertThat(deleteHistories1.equals(deleteHistories2)).isTrue();
    }

    @DisplayName("questionId가 다른 History를 포함하면 같지 않다.")
    @Test
    void ShouldTwoHistoriesNotEqual_whenQuestionIdIsDifferent() {
        DeleteHistories deleteHistories1 = new DeleteHistories();
        DeleteHistories deleteHistories2 = new DeleteHistories();
        deleteHistories1.addHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI);
        deleteHistories2.addHistory(ContentType.QUESTION, NsUserTest.SANJIGI.getId(), NsUserTest.JAVAJIGI);

        assertThat(deleteHistories1.equals(deleteHistories2)).isFalse();
    }

    @DisplayName("writer가 다른 History를 포함하면 같지 않다.")
    @Test
    void ShouldTwoHistoriesNotEqual_whenWriterIsDifferent() {
        DeleteHistories deleteHistories1 = new DeleteHistories();
        DeleteHistories deleteHistories2 = new DeleteHistories();
        deleteHistories1.addHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI);
        deleteHistories2.addHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.SANJIGI);

        assertThat(deleteHistories1.equals(deleteHistories2)).isFalse();
    }

    @DisplayName("questionType이 다른 History를 포함하면 같지 않다.")
    @Test
    void ShouldTwoHistoriesNotEqual_whenQuestionTypeIsDifferent() {
        DeleteHistories deleteHistories1 = new DeleteHistories();
        DeleteHistories deleteHistories2 = new DeleteHistories();
        deleteHistories1.addHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI);
        deleteHistories2.addHistory(ContentType.ANSWER, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI);

        assertThat(deleteHistories1.equals(deleteHistories2)).isFalse();
    }
}
