package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.fixture.QuestionFixture;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswersTest {
    private Answers answers;
    private Question question;

    @BeforeEach
    void setUp() {
        answers = new Answers();
        question = QuestionFixture.create(NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("답변을 추가할 수 있다.")
    void addAnswer() {
        Answer answer = QuestionFixture.createAnswer(NsUserTest.SANJIGI, question);
        answers.addAnswer(answer);

        assertThat(answers.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("답변을 전체 삭제할 수 있다.")
    void delete() throws CannotDeleteException {
        Answer answer1 = QuestionFixture.createAnswer(NsUserTest.JAVAJIGI, question);
        Answer answer2 = QuestionFixture.createAnswer(NsUserTest.JAVAJIGI, question);
        answers.addAnswer(answer1);
        answers.addAnswer(answer2);

        answers.deleteAll(NsUserTest.JAVAJIGI);

        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
    }
}
