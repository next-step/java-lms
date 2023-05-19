package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    private static final Long ANSWER_FIRST_ID = 0L;
    private static final Long ANSWER_SECOND_ID = 1L;
    public static Answer ANSWER_OF_JAVAJIGI = new Answer(ANSWER_FIRST_ID, NsUserTest.JAVAJIGI, QuestionTest.QUESTION_OF_JAVAJIGI, "Answers Contents1");
    public static Answer ANSWER_OF_SANJIGI = new Answer(ANSWER_SECOND_ID, NsUserTest.SANJIGI, QuestionTest.QUESTION_OF_JAVAJIGI, "Answers Contents2");

    @BeforeEach
    void tearDown() {
        ANSWER_OF_JAVAJIGI = new Answer(ANSWER_FIRST_ID, NsUserTest.JAVAJIGI, QuestionTest.QUESTION_OF_JAVAJIGI, "Answers Contents1");
        ANSWER_OF_SANJIGI = new Answer(ANSWER_SECOND_ID, NsUserTest.SANJIGI, QuestionTest.QUESTION_OF_JAVAJIGI, "Answers Contents2");
    }


    @Test
    void 로그인_사용자와_답변한_사람이_다른경우_예외_확인() {
        assertThat(ANSWER_OF_JAVAJIGI.isOwner(NsUserTest.SANJIGI)).isFalse();
    }

    @Test
    void 로그인_사용자와_답변한_사람이_같은지_확인() {
        assertThat(ANSWER_OF_JAVAJIGI.isOwner(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    void 답변_정상적인_삭제_후_삭제_상태_확인() throws CannotDeleteException {
        ANSWER_OF_JAVAJIGI.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(ANSWER_OF_JAVAJIGI.isDeleted()).isTrue();
    }

    @Test
    void 답변_작성자가_아닌_사람이_삭제_시도하고자_하는_경우_예외_검증() {
        assertThatThrownBy(() -> {
            ANSWER_OF_JAVAJIGI.deleteBy(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 답변_삭제시_history_생성_검증() throws CannotDeleteException {
        DeleteHistory deleted = ANSWER_OF_JAVAJIGI.deleteBy(NsUserTest.JAVAJIGI);

        DeleteHistory expected = DeleteHistory.ofAnswer(ANSWER_FIRST_ID, NsUserTest.JAVAJIGI);

        assertThat(ANSWER_OF_JAVAJIGI.isDeleted()).isTrue();
        assertThat(deleted).isEqualTo(expected);
    }
}
