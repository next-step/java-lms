package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.*;
import nextstep.qna.event.QuestionDeleteEvent;
import nextstep.users.domain.NsUser;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("qnaService")
public class QnAService {
    private final QuestionRepository questionRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public QnAService(QuestionRepository questionRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.questionRepository = questionRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void deleteQuestion(NsUser loginUser, long questionId) throws CannotDeleteException {
        Question question = questionRepository.findById(questionId).orElseThrow(NotFoundException::new);
        question.delete(loginUser);

        applicationEventPublisher.publishEvent(new QuestionDeleteEvent(question, loginUser));
    }
}
