package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    NsUser nsUserA;
    NsUser nsUserB;

    Answer answerA1;
    Answer answerA2;
    Answer answerB1;

    @BeforeEach
    void setUp() {
        nsUserA = new NsUser(null, "userIdA", "passwordA", "nameA", "emailA");
        nsUserB = new NsUser(null, "userIdB", "passwordB", "nameB", "emailB");

        answerA1 = new Answer(nsUserA, new Question(), "testA1");
        answerA2 = new Answer(nsUserA, new Question(), "testA2");
        answerB1 = new Answer(nsUserB, new Question(), "testB1");
    }

    @Test
    void isNotOwner_False_테스트() {
        Answers answers = new Answers(Arrays.asList(answerA1, answerA2));
        assertThat(answers.isNotOwner(nsUserA)).isFalse();
    }

    @Test
    void isNotOwner_True_테스트() {
        Answers answers = new Answers(Arrays.asList(answerA1, answerB1));
        assertThat(answers.isNotOwner(nsUserA)).isTrue();
    }

    @Test
    void deleteAll_테스트() {
        Answers answers = new Answers(Arrays.asList(answerA1, answerA2));
        answers.deleteAll();
        assertThat(answerA1.isDeleted()).isTrue();
        assertThat(answerA2.isDeleted()).isTrue();
    }
}
