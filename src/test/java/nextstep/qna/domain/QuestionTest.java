package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

//    @Test
//    void is_owner() throws CannotDeleteException {
//        assertThat(Q1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
//    }
//
//    @Test
//    void can_delete() {
//        Answer answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
//        Q1.addAnswer(answer);
//        assertThatCode(() -> Q1.canDelete(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
//    }
//
//    @Test
//    void can_not_delete() {
//        Answer answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
//        Q1.addAnswer(answer);
//        assertThatThrownBy(() -> Q1.canDelete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
//    }

    @Test
    void delete_question() throws CannotDeleteException {
        Answer answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);

        assertThat(Q1.delete(NsUserTest.JAVAJIGI))
                .hasSize(2)
                .contains(
                        new DeleteHistory(Q1),
                        new DeleteHistory(answer)
                );
    }
}
