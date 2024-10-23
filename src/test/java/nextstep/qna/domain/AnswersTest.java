package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    private Answer answer1;
    private Answer answer2;
    private Answer answer3;
    private Answers answers;
    private Question question;
    private List<DeleteHistory> deleteHistories;

    @BeforeEach
    void setUp() {
        question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        answer1 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        answer2 = new Answer(NsUserTest.SANJIGI, question, "Answers Contents2");
        answer3 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents3");
        answers = new Answers(answer1, answer2);
        deleteHistories = new ArrayList<>();

    }

    @Test
    void create() {
        Answers emptyActual = new Answers();
        Answers actual = new Answers(answer1, answer2);

        assertThat(emptyActual).isNotEqualTo(answers);
        assertThat(actual).isEqualTo(answers);
    }

    @Test
    void add_성공() {
        Answers actual = new Answers();
        actual.add(answer1);
        actual.add(answer2);

        assertThat(actual).isEqualTo(answers);
    }

    @Test
    void deleteAll_성공() throws CannotDeleteException {
        answers = new Answers(answer1, answer3);
        answers.deleteAll(NsUserTest.JAVAJIGI, deleteHistories);

        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer3.isDeleted()).isTrue();
    }

    @Test
    void deleteAll_답변_중_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> {
            answers.deleteAll(NsUserTest.JAVAJIGI, deleteHistories);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
