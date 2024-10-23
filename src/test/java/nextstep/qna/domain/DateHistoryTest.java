package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

/*
- DateHistory List를 생성한다.
*/
public class DateHistoryTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    @DisplayName("DateHistory List를 생성한다.")
    @Test
    void createDeleteHistoryListTest() {
        Q1.addAnswer(A1);

        assertThat(DeleteHistory.createDeleteHistoryList(Q1))
                .extracting("contentType", "contentId", "deletedBy")
                .containsExactly(
                        tuple(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI),
                        tuple(ContentType.ANSWER, null, NsUserTest.JAVAJIGI)
                );
    }
}
