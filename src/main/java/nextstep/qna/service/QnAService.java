package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.answer.Answer;
import nextstep.qna.domain.answer.Answers;
import nextstep.qna.domain.deleteHistory.DeleteHistory;
import nextstep.qna.domain.question.Question;
import nextstep.qna.domain.answer.AnswerRepository;
import nextstep.qna.domain.question.QuestionRepository;
import nextstep.qna.domain.deleteHistory.type.ContentType;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    // 조회
    Question question = questionRepository.findById(questionId).orElseThrow(NotFoundException::new);
    Answers answers = answerRepository.findByQuestion(questionId);

    // 삭제
    question.delete(loginUser, answers);
    answers.deleteAll(question.getWriter());

    // 삭제 히스토리 관리
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter()));
    for (int index = 0; index < answers.size(); index++) {
      Answer answer = answers.get(index);
      deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter()));
    }
    deleteHistoryService.saveAll(deleteHistories);
  }
}
