package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AnswersTest {

    private Answer A1;
    private Answer A2;
    private Answers answers;

    @BeforeEach
    void setUp() {
        answers = new Answers();

        Question q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        this.A1 = new Answer(NsUserTest.JAVAJIGI, q1, "Answers Contents1");
        this.A2 = new Answer(NsUserTest.SANJIGI, q1, "Answers Contents2");
    }
    @DisplayName("답변 목록들을 추가 할 수 있다.")
    @Test
    void name() {
        answers.add(A1);
        answers.add(A2);

        List<Answer> actual = answers.getAnswers();

        assertThat(actual).containsExactly(A1, A2).hasSize(2);
    }

    @DisplayName("컬렉션에 NULL 값을 추가하면 예외가 발생한다.")
    @Test
    void name2() {
        assertThatThrownBy(() -> answers.add(null))
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("답변이 없는 리스트를 삭제해도 예외가 발생하지 않는다.")
    @Test
    void name3() {
        assertThatNoException()
                .isThrownBy(() -> answers.deleteAnswers());
    }
}