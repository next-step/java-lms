package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void owner_여부_확인_테스트() {
        assertThat(A1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(A2.isOwner(NsUserTest.SANJIGI)).isTrue();
    }

    @Test
    void deleted_상태로_변경_테스트() {
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isFalse();
    }
}
