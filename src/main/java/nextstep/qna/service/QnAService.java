package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        Question question = questionRepository.findById(questionId).orElseThrow(NotFoundException::new);
        question.validatePermission(loginUser);

        List<Answer> answers = question.getAnswers();
        answers.forEach(answer -> answer.validatePermission(loginUser));

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        addDeleteHistory(deleteHistories, question.setDeleted(true));
        answers.forEach(answer -> addDeleteHistory(deleteHistories, answer));
        deleteHistoryService.saveAll(deleteHistories);
    }

    private void addDeleteHistory(List<DeleteHistory> deleteHistories, Content content) {
        ContentType contentType = ContentType.findTypeBy(content);
        if (ContentType.ANSWER == contentType) {
            content.setDeleted(true);
        }
        deleteHistories.add(new DeleteHistory(contentType, content.getId() , content.getWriter(), LocalDateTime.now()));
    }
}
