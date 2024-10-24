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
    void delete_성공() throws CannotDeleteException {
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        deleteHistories = question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
        assertThat(deleteHistories).isEqualTo(new DeleteHistories(new DeleteHistory(question), new DeleteHistory(answer1), new DeleteHistory(answer2)));
    }

    @Test
    void delete_성공_답변이_없는경우() throws CannotDeleteException {
        deleteHistories = question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).isEqualTo(new DeleteHistories(new DeleteHistory(question)));
    }

    @Test
    void delete_다른사람이_쓴_글() {
        assertThatThrownBy(() -> {
            deleteHistories = question.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_답변_중_다른사람이_쓴_답변() {
        assertThatThrownBy(() -> {
            question.addAnswer(anotherWriteAnswer);
            deleteHistories = question.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
