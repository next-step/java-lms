package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class AnswersTest {

    @Test
    public void 모든답변은_오너가_작성해야_삭제가능하다() {
        Answers answers = new Answers();
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents3"));

        // then
        assertThat(answers.canBeDeletedBy(NsUserTest.JAVAJIGI)).isFalse();
    }

    @Test
    public void 삭제_실행시_owner가_같은경우만_deleted_flag가_true() {
        Answer a1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer a2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        Answer a3 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents3");
        Answers answers = new Answers();
        answers.add(a1);
        answers.add(a2);
        answers.add(a3);

        // when
        answers.deleteAll(NsUserTest.JAVAJIGI);

        // then
        assertThat(a2.isDeleted()).isFalse();
        assertThat(a1.isDeleted()).isTrue();
        assertThat(a3.isDeleted()).isTrue();
    }
}
