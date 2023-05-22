package nextstep.qna.domain;

import java.util.List;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;

public class AnswerFixture {

    public static Answers JAVAJIGI_답변_복수_개() {
        Question question = new Question(JAVAJIGI, "title", "question");
        return new Answers(
                List.of(
                        new Answer(JAVAJIGI, question, "answer1"),
                        new Answer(JAVAJIGI, question, "answer2"),
                        new Answer(JAVAJIGI, question, "answer3")
                )
        );
    }

    public static Answers SANJIGI_답변_복수_개() {
        Question question = new Question(SANJIGI, "title", "question");
        return new Answers(
                List.of(
                        new Answer(SANJIGI, question, "answer1"),
                        new Answer(SANJIGI, question, "answer2"),
                        new Answer(SANJIGI, question, "answer3")
                )
        );
    }
}
