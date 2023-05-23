package nextstep.qna.service;

import java.util.List;
import javax.annotation.Resource;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionDeleteHistory;
import nextstep.qna.domain.QuestionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

  @Resource(name = "questionRepository")
  private final QuestionRepository questionRepository;

  private final AnswerService answerService;

  public QuestionService(QuestionRepository questionRepository, AnswerService answerService) {
    this.questionRepository = questionRepository;
    this.answerService = answerService;
  }

  public Question getQuestionOrThrowIfNotExist (long questionId) {
    return questionRepository.findById(questionId)
        .orElseThrow(NotFoundException::new);
  }

  public QuestionDeleteHistory delete(NsUser loginUser, Question question) throws CannotDeleteException {
    if (!question.isOwner(loginUser)) {
      throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
    }

    question.setDeleted(true);
    List<Answer> answers = answerService.deleteAnswers(loginUser, question);

    return new QuestionDeleteHistory(question, answers);
  }
}
