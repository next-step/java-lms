package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    public static Question question = new Question(3L, NsUserTest.JAVAJIGI, new QuestionContents("title1", "contents1"));
    public static Answer A1 = new Answer(1L, NsUserTest.JAVAJIGI, question, new AnswerContents("Answers Contents1"));
    public static Answer A2 = new Answer(2L, NsUserTest.SANJIGI, question, new AnswerContents("Answers Contents2"));

    private Answers answers = new Answers(question);

    @BeforeEach
    void setUp() {
        A1 = new Answer(1L, NsUserTest.JAVAJIGI, question, new AnswerContents("Answers Contents1"));
        answers.addAnswer(A1);
    }

    @Test
    @DisplayName("[요구사항_1] 질문자와 답변 작성자가 다를 경우 삭제할 수 없다는 Exception을 던진다.")
    void 요구사항_1() {
        // given : SANJIGI가 JAVAJIGI의 질문에 답변
        A2 = new Answer(2L, NsUserTest.SANJIGI, question, new AnswerContents("Answers Contents2"));
        answers.addAnswer(A2);

        // then : 질문자는 JAVAJIGI, 답변자 중 SANJIGI가 있으므로 삭제 불가
        assertThatThrownBy(() -> answers.deleteAll())
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("[요구사항_2] 질문자와 답변 작성자가 같을 경우 성공적으로 삭제가 이루어진다.")
    void 요구사항_2() throws CannotDeleteException {
        // when : 질문자이자 답변자인 JAVAJIGI의 답변 삭제
        answers.deleteAll();

        // then : 전체 삭제 여부 확인
        assertThat(answers.isAllDeleted()).isTrue();
    }
}
