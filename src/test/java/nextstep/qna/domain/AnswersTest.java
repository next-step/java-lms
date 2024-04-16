package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnswersTest {
    public static final Answers answers = new Answers();
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("delete()")
    public void delete() {
        answers.add( A1 );
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));

        Assertions.assertThat(answers.delete()).isEqualTo(deleteHistories);
    }

    @Test
    @DisplayName("throwsCannotDeleteExceptionIfCannotDelete()")
    public void throwsCannotDeleteExceptionIfCannotDelete() {
        answers.add( A1 );
        answers.add( A2 );
        Assertions.assertThatThrownBy(() -> {
            answers.throwsCannotDeleteExceptionIfCannotDelete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }



}
