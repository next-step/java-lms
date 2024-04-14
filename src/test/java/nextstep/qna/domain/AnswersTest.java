package nextstep.qna.domain;

import java.util.List;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    private static Answers ANSWERS1;

    @BeforeEach
    void init() {
        ANSWERS1 = new Answers(List.of(AnswerTest.A1));
    }

    @Test
    void add() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        assertThat(answers).isEqualTo(ANSWERS1);
    }

    @Test
    void isContainOtherOwner() {
        boolean result = ANSWERS1.isContainOtherOwner(NsUserTest.JAVAJIGI);
        assertThat(result).isTrue();
    }

    @Test
    void delete() {
        Answer expectedAnswer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        expectedAnswer.delete();

        Answers expected = new Answers(List.of(expectedAnswer));
        ANSWERS1.delete();
        assertThat(ANSWERS1).isEqualTo(expected);
    }

    @Test
    void getDeleteHistory() {
        List<DeleteHistory> deleteHistories = ANSWERS1.getDeleteHistory();
        List<DeleteHistory> expected = List.of(DeleteHistoryTest.H1);

        assertThat(deleteHistories).isEqualTo(expected);
    }
}
