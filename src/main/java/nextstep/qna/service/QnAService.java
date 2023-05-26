package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service("qnaService")
public class QnAService {
    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "answerRepository")
    private AnswerRepository answerRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional
    public void deleteQuestion(NsUser loginUser, long questionId) throws CannotDeleteException {
        Question question = findQuestionByQuestionId(questionId);
        validateIfLoginUserHasAuthorityToDeleteQuestion(loginUser, question);

        question.answersCanBeDeleted(loginUser);

        question.delete(true);
        addQuestionToDeleteHistories(question);

        deleteAnswersAndAddThemToDeleteHistories(question);
        deleteHistoryService.saveAll();
    }

    private Question findQuestionByQuestionId(long questionId) {
        return questionRepository.findById(questionId).orElseThrow(NotFoundException::new);
    }

    private void validateIfLoginUserHasAuthorityToDeleteQuestion(NsUser loginUser, Question question) throws CannotDeleteException {
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private void deleteAnswersAndAddThemToDeleteHistories(Question question) {
        for (Answer answer : question.getListOfAnswers()) {
            answer.delete(true);
            addAnswerToDeleteHistories(answer);
        }
    }

    private void addAnswerToDeleteHistories(Answer answer) {
        DeleteHistory deleteHistoryAnswer = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());
        deleteHistoryService.addDeleteHistory(deleteHistoryAnswer);
    }

    private void addQuestionToDeleteHistories(Question question) {
        DeleteHistory deleteHistoryQuestion = new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now());
        deleteHistoryService.addDeleteHistory(deleteHistoryQuestion);
    }
}
