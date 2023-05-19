package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    void 로그인_사용자와_답변한_사람이_다른경우_예외_확인() {
        assertThat(A1.isOwner(NsUserTest.SANJIGI)).isFalse();
    }

    @Test
    void 로그인_사용자와_답변한_사람이_같은지_확인() {
        assertThat(A1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    void 답변_삭제_상태로_변경_후_삭제_상태_확인() {
        A1.deleted();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void 답변_삭제시_history_생성_검증() {
        DeleteHistory deleted = A1.deleted();

        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER,null, NsUserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleted).isEqualTo(expected);
    }
}
