package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AnswersTest {

    private Question Q1;
    private Answer A1;
    private Answer A2;
    private Answers answers;
    private DeleteHistories deleteHistories;

    @BeforeEach
    void setUp() {
        deleteHistories = new DeleteHistories();
        this.answers= new Answers();

        this.Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        this.A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        this.A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
    }

    @Test
    @DisplayName("Answers 일급 컬렉션에 답변 객체를 추가할 수 있다.")
    void enroll_AddAnswer_Success() {
        answers.add(A1);
        answers.add(A2);

        List<Answer> answerList = answers.getAnswers();

        Assertions.assertThat(answerList).containsExactly(A1, A2).hasSize(2);
    }

    @Test
    @DisplayName("Answers 일급 컬렉션에 null이 추가되면 예외를 던진다.")
    void enroll_AddNull_ThrowException() {
        Assertions.assertThatThrownBy(() -> answers.add(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("답변이 없는 리스트를 삭제해도 예외가 발생하지 않는다.")
    void delete_EmptyAnswersDelete_NotThrowException() {
        Assertions.assertThatNoException()
                .isThrownBy(() -> answers.deleteAnswers(deleteHistories));
    }
}
