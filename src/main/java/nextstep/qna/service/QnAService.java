package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerRepository;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

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
        question = question.canDelete(loginUser);
        question = question.setDeleted(TRUE);

        List<DeleteHistory> deleteHistories = convertToDeleteHistory(questionId, question);
        deleteHistoryService.saveAll(deleteHistories);
    }

    private List<DeleteHistory> convertToDeleteHistory(long questionId, Question question) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(question.toDeleteHistory(questionId));

        List<Answer> answers = question.getAnswers();
        answers.forEach(answer -> deleteHistories.add(answer.toDeleteHistory()));

        return deleteHistories;
    }
}
