package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnswersTest {

    private final Answers AS1 = new Answers();

    private final Answers AS2 = new Answers();
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @BeforeEach
    void init(){
        AS2.add(A1);
        AS2.add(A2);
    }

    @Test
    void isEmpty(){
        boolean AS1result = AS1.isEmpty();
        boolean AS2result = AS2.isEmpty();

        assertAll(
                ()->assertThat(AS1result).isTrue(),
                ()->assertThat(AS2result).isFalse()
        );

    }


}
