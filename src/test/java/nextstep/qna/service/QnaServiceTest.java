package nextstep.qna.service;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.qna.domain.QuestionTest;
import nextstep.qna.exception.QuestionDeleteUnauthorizedException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static nextstep.qna.domain.DeleteHistory.deleteHistoryHelper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    private static Question ba;
    private static Question JAVAJIGI_QUESTION;
    private static Question SANJIGI_QUESTION;
    private static Answer JAVAJIGI_ANSWER;
    private static Answer SANJIGI_ANSWER;

    @BeforeEach
    public void setUp() {

    }

    @DisplayName("자신이 작성한 글 & 댓글이 달리지 않은 경우 : 삭제에 성공한다")
    @Test
    public void delete_성공() {
        //given
        Question question = BADAJIGI_QUESTION;
        NsUser loginUser = NsUserTest.BADAJIGI
        //when
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
        qnAService.deleteQuestion(loginUser, question.getId());

        //then
        assertAll("",
                () -> assertThat(question.isDeleted()).isTrue(),
                () -> assertThat(JAVAJIGI_ANSWER.isDeleted()).isFalse(),
                () -> assertThat(SANJIGI_ANSWER.isDeleted()).isFalse(),
                () -> verifyDeleteHistories(deleteHistoryHelper(List.of(question), new ArrayList<>()))
        );
    }


    @DisplayName("자신이 작성한 글 & 자신의 댓글의 경우 : 삭제에 성공한다")
    @Test
    public void delete_성공_질문자_답변자_같음() {
        //given
        JAVAJIGI_QUESTION.addAnswer(JAVAJIGI_ANSWER);
        Question question = JAVAJIGI_QUESTION;
        Answer answer = JAVAJIGI_ANSWER;
        NsUser loginUser = NsUserTest.JAVAJIGI;

        //when
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
        qnAService.deleteQuestion(loginUser, question.getId());

        //then
        assertAll("질문글 삭제에 성공했는지 검증한다",
                () -> assertThat(question.isDeleted())
                        .as("question 은 삭제 되어야 한다")
                        .isTrue(),
                () -> assertThat(answer.isDeleted())
                        .as("answer 는 삭제 되어야 한다")
                        .isTrue(),
                () -> verifyDeleteHistories(deleteHistoryHelper(List.of(question), List.of(answer)))
        );
    }

    @DisplayName("타인이 작성한 글 & 타인이 작성한 댓글이 달려있는 경우 : QuestionDeleteUnauthorizedException 을 던진다")
    @Test
    public void delete_다른_사람이_쓴_글과댓글() throws Exception {
        //given
        JAVAJIGI_QUESTION.addAnswer(JAVAJIGI_ANSWER);
        Question question = JAVAJIGI_QUESTION;

        //when
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        //then
        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, question.getId());
        })
                .isInstanceOf(QuestionDeleteUnauthorizedException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다");
    }

    @DisplayName("다른사람이 쓴 질문글의 경우 : QuestionDeleteUnauthorizedException 를 던진다 ")
    @Test
    public void delete_답변_중_다른_사람이_쓴_글() {
        //given
        JAVAJIGI_QUESTION.addAnswer(SANJIGI_ANSWER);
        Question question = JAVAJIGI_QUESTION;

        //when
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        //then
        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, question.getId());
        }).isInstanceOf(QuestionDeleteUnauthorizedException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다");
    }

    private void verifyDeleteHistories(List<DeleteHistory> deleteHistories) {
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
