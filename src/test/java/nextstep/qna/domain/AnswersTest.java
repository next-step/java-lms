package nextstep.qna.domain;

import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    public static final Answers ANSWERS1 = new Answers(List.of(AnswerTest.A1, AnswerTest.A3));
    public static final Answers ANSWERS2 = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));
    public static final Answers ANSWERS3 = new Answers();

    @Test
    void 답변_삭제_후_결과_확인() throws CannotDeleteException {
        ANSWERS1.delete(NsUserTest.JAVAJIGI);
        assertThat(ANSWERS1.isAllDeleted()).isTrue();
    }

    @Test
    void 답변_추가() {
        ANSWERS3.add(AnswerTest.A2);
        assertThat(ANSWERS3.getAnswers()).hasSize(1);
    }

    @Test
    void 삭제_이력() throws CannotDeleteException {
        ANSWERS1.delete(NsUserTest.JAVAJIGI);
        assertThat(ANSWERS1.getDeleteHistories()).hasSize(2);
    }

}
