package nextstep.qna.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerRepository;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Question deletedQuestion = questionRepository.findById(questionId)
            .orElseThrow(NotFoundException::new)
            .delete(loginUser);

        List<Answer> deletedAnswers = deletedQuestion.getAnswers().stream().map(answer -> {
            try {
                return answer.delete(loginUser);
            } catch (CannotDeleteException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        deleteHistoryService.saveAll(deletedQuestion, deletedAnswers);
    }
}
