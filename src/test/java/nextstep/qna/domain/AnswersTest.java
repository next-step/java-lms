package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AnswersTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    private Answers answers = new Answers();

    @DisplayName("답변_리스트_추가")
    @Test
    void 답변_추가기능() {
        answers.addAnswer(A1);
        answers.addAnswer(A2);

        assertThat(answers.count()).isEqualTo(2);
    }

    @DisplayName("답변_전체삭제")
    @Test
    void 답변_전체삭제기능() throws CannotDeleteException {
        answers.addAnswer(A1);
        answers.addAnswer(A3);

        answers.deleteAnswers(NsUserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(A3.isDeleted()).isTrue();
    }

    @DisplayName("답변_전체삭제_질문자와답변자가_다른경우")
    @Test
    void 답변_전체삭제기능_권한없는경우() {
        answers.addAnswer(A1);
        answers.addAnswer(A2);

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answers.deleteAnswers(NsUserTest.JAVAJIGI))
                .withMessageMatching("삭제할 권한이 없습니다.");
    }
}


