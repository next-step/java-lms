package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");

    @Test
    @DisplayName("답변을 작성한 사람이 삭제 요청하면 삭제한다.")
    void deleteTest() throws Exception {
        A1.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변을 작성한 사람이 아닌데 삭제 요청하면 예외가 발생한다.")
    void failDeleteTest() {
        assertThatThrownBy(() -> A2.deleteBy(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
