package fixture.qna.domain;

import java.util.List;

import nextstep.qna.domain.AnswerTest;
import nextstep.qna.domain.Answers;

public enum AnswersFixture {
    다른_사람의_답변이_있는_답변들(new Answers(List.of(AnswerTest.A1, AnswerTest.A2))),
    같은_사람의_답변이_있는_답변들(new Answers(List.of(AnswerTest.A1, AnswerTest.A1)));

    private Answers answers;

    AnswersFixture(Answers answers) {
        this.answers = answers;
    }

    public Answers getAnswers() {
        return answers;
    }
}
