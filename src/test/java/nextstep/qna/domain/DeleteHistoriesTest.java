package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

public class DeleteHistoriesTest {
    @Test
    void ShouldTwoHistoriesBeEqual_whenEmptyHistories() {
        DeleteHistories deleteHistories1 = new DeleteHistories();
        DeleteHistories deleteHistories2 = new DeleteHistories();

        assertThat(deleteHistories1.equals(deleteHistories2)).isTrue();
    }

    @Test
    void ShouldTwoHistoriesBeEqual_whenIncludingHistoryIsSame() {
        DeleteHistories deleteHistories1 = new DeleteHistories();
        DeleteHistories deleteHistories2 = new DeleteHistories();
        deleteHistories1.addHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI);
        deleteHistories2.addHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI);

        assertThat(deleteHistories1.equals(deleteHistories2)).isTrue();
    }

    @Test
    void ShouldTwoHistoriesNotEqual_whenQuestionIdIsDifferent() {
        DeleteHistories deleteHistories1 = new DeleteHistories();
        DeleteHistories deleteHistories2 = new DeleteHistories();
        deleteHistories1.addHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI);
        deleteHistories2.addHistory(ContentType.QUESTION, NsUserTest.SANJIGI.getId(), NsUserTest.JAVAJIGI);

        assertThat(deleteHistories1.equals(deleteHistories2)).isFalse();
    }

    @Test
    void ShouldTwoHistoriesNotEqual_whenWriterIsDifferent() {
        DeleteHistories deleteHistories1 = new DeleteHistories();
        DeleteHistories deleteHistories2 = new DeleteHistories();
        deleteHistories1.addHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI);
        deleteHistories2.addHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.SANJIGI);

        assertThat(deleteHistories1.equals(deleteHistories2)).isFalse();
    }

    @Test
    void ShouldTwoHistoriesNotEqual_whenQuestionTypeIsDifferent() {
        DeleteHistories deleteHistories1 = new DeleteHistories();
        DeleteHistories deleteHistories2 = new DeleteHistories();
        deleteHistories1.addHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI);
        deleteHistories2.addHistory(ContentType.ANSWER, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI);

        assertThat(deleteHistories1.equals(deleteHistories2)).isFalse();
    }
}
