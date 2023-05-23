package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionDeleteHistory;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("qnaService")
public class QnAService {

    private final QuestionService questionService;
    private final DeleteHistoryService deleteHistoryService;

    public QnAService(QuestionService questionService, DeleteHistoryService deleteHistoryService) {
        this.questionService = questionService;
        this.deleteHistoryService = deleteHistoryService;
    }

    @Transactional
    public Question deleteQuestion(NsUser loginUser, long questionId) throws CannotDeleteException {
        Question question = questionService.getQuestionOrThrowIfNotExist(questionId);
        QuestionDeleteHistory deleteHistory = questionService.delete(loginUser, question);

        deleteHistoryService.saveAll(deleteHistory.getDeleteHistories());
        return question;
    }
}
