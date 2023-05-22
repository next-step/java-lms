package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1" );
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2" );
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");


    @Test
    public void delete_by_loginUser() {
        assertAll(
                () -> assertDoesNotThrow(() -> Q1.delete(NsUserTest.JAVAJIGI)),
                () -> assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                        .isInstanceOf(CannotDeleteException.class)
        );
    }

    @Test
    public void delete_only_question() throws CannotDeleteException {
        // when
        List<DeleteHistory> delete = Q1.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(delete.size()).isEqualTo(1);
    }

    @Test
    public void delete_with_answers() throws CannotDeleteException {
        // given
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);

        // when
        List<DeleteHistory> delete = Q1.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(delete.size()).isEqualTo(3);
    }
}
