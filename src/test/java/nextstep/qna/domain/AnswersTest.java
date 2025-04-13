package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    Answer A1;
    Answer A2;
    Answer A3;
    Question Q1;

    @BeforeEach
    public void init() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
        A3 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents3");
    }

    @Test
    @DisplayName("답변의 작성자가 전부 같지 않으면 에러를 반환한다.")
    void isAllAnswerOwner_false() {
        Answers answers = new Answers(List.of(A1, A2, A3));
        assertThatThrownBy(() -> answers.deleteAll(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변의 작성자가 전부 같으면 true 반환한다.")
    void isAllAnswerOwner_true() throws CannotDeleteException {
        Answers answers = new Answers(List.of(A1, A3));
        answers.deleteAll(NsUserTest.JAVAJIGI);
    }
}