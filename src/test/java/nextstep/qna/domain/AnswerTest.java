package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {

    @Test
    void 로그인_유저와_답변_작성자가_다름() {
        Answer answer = answer();
        Assertions.assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> answer.delete(NsUserTest.SANJIGI))
            .withMessage("본인이 작성한 답변만 삭제할 수 있습니다.");
    }

    @Test
    void 질문_작성자_로그인_유저_답변_작성자_모두_같음() throws Exception {
        Answer answer = answer();
        answer.delete(NsUserTest.JAVAJIGI);
        assertThat(answer.isDeleted()).isTrue();
    }

    private static Answer answer() {
        NsUser writer = NsUserTest.JAVAJIGI;
        Question question = new Question(writer, "title1", "contents1");
        return new Answer(writer, question, "Answers Contents1");
    }
}
