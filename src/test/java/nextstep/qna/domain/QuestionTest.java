package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    private Question question;
    private Answer answer1;
    private Answer answer2;
    private Answer anotherWriteAnswer;
    private DeleteHistories deleteHistories;

    @BeforeEach
    void setUp() {
        question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        answer1 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        answer2 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents2");
        anotherWriteAnswer = new Answer(NsUserTest.SANJIGI, question, "Answers Contents3");
        deleteHistories = new DeleteHistories();
    }

    @Test
    void deleteAll_성공() throws CannotDeleteException {
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        Question deletedQuestion = question.deleteAll(NsUserTest.JAVAJIGI, deleteHistories);

        assertThat(question.isDeleted()).isTrue();
        assertThat(deletedQuestion.isDeleted()).isTrue();
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
        assertThat(deleteHistories).isEqualTo(new DeleteHistories(new DeleteHistory(question), new DeleteHistory(answer1), new DeleteHistory(answer2)));
    }

    @Test
    void deleteAll_성공_답변이_없는경우() throws CannotDeleteException {
        Question deletedQuestion = question.deleteAll(NsUserTest.JAVAJIGI, deleteHistories);

        assertThat(deletedQuestion.isDeleted()).isTrue();
        assertThat(deleteHistories).isEqualTo(new DeleteHistories(new DeleteHistory(question)));
    }

    @Test
    void deleteAll_다른사람이_쓴_글() {
        assertThatThrownBy(() -> {
            Question deletedQuestion = question.deleteAll(NsUserTest.SANJIGI, deleteHistories);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void deleteAll_답변_중_다른사람이_쓴_답변() {
        assertThatThrownBy(() -> {
            question.addAnswer(anotherWriteAnswer);
            question.deleteAll(NsUserTest.JAVAJIGI, deleteHistories);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
