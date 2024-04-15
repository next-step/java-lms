package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.DeleteHistories;
import nextstep.qna.domain.DeleteHistoryEvent;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("qnaService")
public class QnAService {
    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Transactional
    public void deleteQuestion(NsUser loginUser, long questionId) throws CannotDeleteException {
        Question question = questionRepository.findById(questionId).orElseThrow(NotFoundException::new);

        question.delete(loginUser);

        publisher.publishEvent(new DeleteHistoryEvent(this, DeleteHistories.of(question)));
    }
}
