package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @Test
    public void 모든_답변이_같은작성자면_삭제_가능() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer1 = new Answer(11L, NsUserTest.JAVAJIGI, question, "answer1");
        Answer answer2 = new Answer(12L, NsUserTest.JAVAJIGI, question, "answer2");
        Answers answers = new Answers(Arrays.asList(answer1, answer2));

        List<DeleteHistory> delete = answers.delete(NsUserTest.JAVAJIGI);
    }

    @Test
    public void 다른_작성자의_답변이_있으면_예외() {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer1 = new Answer(11L, NsUserTest.JAVAJIGI, question, "answer1");
        Answer answer2 = new Answer(12L, NsUserTest.SANJIGI, question, "answer2");
        Answers answers = new Answers(Arrays.asList(answer1, answer2));

        assertThatThrownBy(() -> {
            answers.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람의 답변이 존재하여 삭제할 수 없습니다.");
    }

    @Test
    public void 모든_답변_삭제_및_DeleteHistory_반환() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer1 = new Answer(11L, NsUserTest.JAVAJIGI, question, "answer1");
        Answer answer2 = new Answer(12L, NsUserTest.JAVAJIGI, question, "answer2");
        Answers answers = new Answers(Arrays.asList(answer1, answer2));

        List<DeleteHistory> deleteHistories = answers.delete(NsUserTest.JAVAJIGI);

        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(2);
        assertThat(deleteHistories.get(0).getContentType()).isEqualTo(ContentType.ANSWER);
        assertThat(deleteHistories.get(1).getContentType()).isEqualTo(ContentType.ANSWER);
    }
}
