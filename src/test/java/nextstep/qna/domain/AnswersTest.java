package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    public static Answers answers;

    @BeforeEach
    public void 데이터_초기화() {
        Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        List<Answer> list = Arrays.asList(new Answer(NsUserTest.JAVAJIGI, Q1, "a1-contents"),
                new Answer(NsUserTest.JAVAJIGI, Q1, "a2-contents"));
        answers = new Answers(list);
    }


    @DisplayName("[삭제성공]질문 작성자, 답변 작성자 일치")
    @Test
    public void 답변_리스트_삭제() throws Exception {
        assertThat(answers.delete(NsUserTest.JAVAJIGI))
                .isNotNull();
    }

    @DisplayName("[삭제실패]질문 작성자, 답변 작성자 불일치")
    @Test
    public void 답변_리스트_삭제_예외발생() throws Exception {
        assertThatThrownBy(() -> answers.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
