package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QnADeleteService {
    private final QuestionService questionService;
    private final DeleteHistoryService deleteHistoryService;

    public QnADeleteService(QuestionService questionService,
            DeleteHistoryService deleteHistoryService) {
        this.questionService = questionService;
        this.deleteHistoryService = deleteHistoryService;
    }

    @Transactional
    public void deleteQuestion(NsUser loginUser, long questionId) throws CannotDeleteException {
        Question question = questionService.findById(questionId);
        question.deleteQuestion(loginUser);
        deleteHistoryService.saveAll(question.deleteHistories());
    }
}
