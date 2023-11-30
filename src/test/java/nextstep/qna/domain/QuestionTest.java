package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(1L, NsUserTest.SANJIGI, "title1", "contents1");
    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        answer = new Answer(11L, NsUserTest.JAVAJIGI, question, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    @DisplayName("질문, 답변 삭제 성공")
    void question_delete_test() throws CannotDeleteException {
        // given
        NsUser sanjigi = NsUserTest.JAVAJIGI;

        // when
        question.delete(sanjigi);

        // then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문, 답변 삭제 실패 - 질문 작성자가 아닌 유저가 질문을 지울 때")
    void question_delete__fail_test() {
        // given
        NsUser differentWriter = NsUserTest.SANJIGI;

        // then
        assertThrows(CannotDeleteException.class ,
                    () ->  question.delete(differentWriter));
    }

    @Test
    @DisplayName("질문 삭제시 답변에 다른 사람이 작성한 답변있을 경우 삭제 불가")
    public void qeustion_fail_cause_answer_writer() {
      // given
      Answer differentAuthorAnswer = new Answer(13L, NsUserTest.SANJIGI, question, "Answer Content2");
      question.addAnswer(differentAuthorAnswer);

      // then
      assertThrows(CannotDeleteException.class ,
                   () ->  question.delete(NsUserTest.JAVAJIGI));
    }
}
