package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AnswersTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1" );
    public static final Answers AS1 = new Answers(Q1);

    @Test
    public void delete() {
        assertAll(
                () -> assertDoesNotThrow(() -> AS1.delete(NsUserTest.JAVAJIGI))
        );
    }
}
