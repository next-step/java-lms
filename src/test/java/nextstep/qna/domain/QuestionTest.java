package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    public static final Question 답변_없는_질문 = new Question(NsUserTest.SANJIGI, "title2", "contents2", null);

    public static final Question 답변_있는_질문 = new Question(NsUserTest.SANJIGI, "title2", "contents2", new Answers(Arrays.asList(A1, A2)));

    public static final Question 내가_쓰지_않은_답변이_있는_질문 = new Question(NsUserTest.SANJIGI, "title2", "contents2", new Answers(Arrays.asList(A1, A2)));

    public static final Question 내가_쓴_답변만_있는_질문 = new Question(NsUserTest.SANJIGI, "title2", "contents2", new Answers(Arrays.asList(A2)));

    @Test
    @DisplayName("로그인 사용자와 질문한 사용자가 같은지 확인한다")
    void 로그인_사용자와_질문한_사용자가_다르면_예외_발생() {
        assertThatThrownBy(() -> {
            Q1.isOwner(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변이 없으면 삭제 할 수 있다.")
    void 답변이_없으면_삭제_할_수_있다() throws CannotDeleteException {
        답변_없는_질문.delete(NsUserTest.SANJIGI);
        assertThat(답변_없는_질문.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변이 있으면 삭제 할 수 없다.")
    void 답변이_있으면_삭제_할_수_없다() {
        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
            답변_있는_질문.delete(NsUserTest.SANJIGI);
        }).withMessage("답변이 있는 경우 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("답변자가 다른 답변이 있으면 예외를 발생한다.")
    void 삭제할_수_없는_답변_예외발생(){
        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
            내가_쓰지_않은_답변이_있는_질문.delete(NsUserTest.SANJIGI);
        }).withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }


    @Test
    @DisplayName("질문 삭제 되면 답변 모두도 삭제된다.")
    void 삭제된_질문_답변_삭제() throws CannotDeleteException {
        내가_쓴_답변만_있는_질문.delete(NsUserTest.SANJIGI);
        assertThat(내가_쓴_답변만_있는_질문.getAnswers().isAllDeleted()).isTrue();
    }
}