package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("다른 사람이 쓴 답변이 있을 경우 삭제 불가")
    void isDeletableTest() {
        Answers multiUserAnswers = new Answers(List.of(A1, A2));

        assertThatThrownBy(() -> multiUserAnswers.isDeletable(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}