package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnswersTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    @Test
    @DisplayName("Answers 정상 삭제")
    void deleteNormal() {
        //given
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A3);

        // when
        Assertions.assertDoesNotThrow(() -> {
            answers.delete(NsUserTest.JAVAJIGI);
        });

        //then
        assertThat(A1.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("Answers 삭제 테스트 에러 발생")
    void deleteError() {
        //given
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A2);

        //when //then
        Assertions.assertThrows(CannotDeleteException.class, () -> {
            answers.delete(NsUserTest.SANJIGI);
        });
    }
}
