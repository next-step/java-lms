package nextstep.qna.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {
    @Test
    public void Add() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThat(answers).isEqualTo(new Answers(List.of(AnswerTest.A1, AnswerTest.A2)));
    }
}
