package nextstep.qna.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nextstep.qna.NotFoundException;
import nextstep.qna.domain.AnswerRepository;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.users.domain.NsUser;

@Service("qnaService")
public class QnAService {

    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "answerRepository")
    private AnswerRepository answerRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional
    public void deleteQuestion(final NsUser loginUser, final long questionId) {
        final Question question = questionRepository.findById(questionId)
                .orElseThrow(NotFoundException::new);

        // if (!question.isOwner(loginUser)) {
        //     throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        // }
        //
        // final List<Answer> answers = question.getAnswers();
        // for (Answer answer : answers) {
        //     if (!answer.isOwner(loginUser)) {
        //         throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        //     }
        // }
        //
        // final List<DeleteHistory> deleteHistories = new ArrayList<>();
        // question.setDeleted(true);
        // deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));
        //
        // for (Answer answer : answers) {
        //     answer.setDeleted(true);
        //     deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        // }

        final List<DeleteHistory> deleteHistories = question.deleteBy(loginUser, LocalDateTime.now());

        deleteHistoryService.saveAll(deleteHistories);
    }
}
