package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    private Question question;

    @BeforeEach
    public void setUp() {
        question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        question.addAnswer(new Answer(12L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));
    }

    @Test
    void 답변을_추가한다() {
        // given
        Answers answers = new Answers();

        // when
        boolean result = answers.add(AnswerTest.A1);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    void 답변을_모두_삭제한다() throws Exception {
        // given
        NsUser loginUser = NsUserTest.JAVAJIGI;
        Answers answers = question.getAnswers();

        // when
        List<DeleteHistory> result = answers.deleteAll(loginUser);

        // then
        assertThat(result).contains(
                new DeleteHistory(ContentType.ANSWER, 11L, NsUserTest.JAVAJIGI, LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, 12L, NsUserTest.JAVAJIGI, LocalDateTime.now())
        );
    }
}
