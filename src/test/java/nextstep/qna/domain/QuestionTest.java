package nextstep.qna.domain;

import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    @Test
    void 로그인_유저와_질문자가_다름() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> question.delete(NsUserTest.SANJIGI))
            .withMessage("본인이 작성한 질문만 삭제할 수 있습니다.");
    }

    @Test
    void 질문자와_답변자가_다름() {
        Question q = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answers a = new Answers(List.of(
            new Answer(NsUserTest.JAVAJIGI, q, ""),
            new Answer(NsUserTest.SANJIGI, q, "")
        ));
        Question question = new Question(NsUserTest.JAVAJIGI, a);
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> question.delete(NsUserTest.JAVAJIGI));
    }

    @Test
    void 답변_없음() throws Exception {
        Question question = new Question(NsUserTest.SANJIGI, "title1", "contents1");
        question.delete(NsUserTest.SANJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void 질문_작성자_로그인_유저_답변_작성자_모두_같음() throws Exception {
        Question question = question();
        question.delete(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
        assertThat(question.getAnswers().isAllDeleted()).isTrue();
    }

    @Test
    void 삭제_이력_있음() throws Exception {
        Question question = question();
        question.delete(NsUserTest.JAVAJIGI);
        assertThat(question.getDeleteHistories()).hasSize(3);
    }

    private static Question question() {
        NsUser writer = NsUserTest.JAVAJIGI;
        Question q = new Question(writer, "title1", "contents1");
        Answers a = new Answers(List.of(
            new Answer(writer, q, ""),
            new Answer(writer, q, "")
        ));
        return new Question(writer, a);
    }

}
