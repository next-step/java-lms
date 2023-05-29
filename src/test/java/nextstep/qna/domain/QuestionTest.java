package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {

    public static final Question QUESTION_1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question QUESTION_2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    public static Answer answer1;
    public static Answer answer2;

    @BeforeEach
    void init(){
        answer1 = new Answer(NsUserTest.JAVAJIGI, QUESTION_1, "Answers Contents1");
        answer2 = new Answer(NsUserTest.SANJIGI, QUESTION_2, "Answers Contents1");
    }

    @Test
    void deleteTest() throws CannotDeleteException {
        var deleteHistories = QUESTION_1.delete(NsUserTest.JAVAJIGI);

        System.out.println(deleteHistories);
    }

    @Test
    void deleteAnswersTest() throws CannotDeleteException {
        QUESTION_1.addAnswer(answer1);
        QUESTION_1.deleteAnswers(NsUserTest.JAVAJIGI);

        assertThat(QUESTION_1.isDeleted()).isTrue();
    }
}
