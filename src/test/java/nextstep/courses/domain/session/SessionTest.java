package nextstep.courses.domain.session;

import nextstep.courses.domain.participant.ParticipantManager;
import nextstep.courses.domain.participant.SessionUserEnrolment;
import nextstep.courses.exception.*;
import nextstep.courses.type.RecruitmentStatus;
import nextstep.courses.type.SessionStatus;
import nextstep.courses.type.ParticipantSelectionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    private static Session createSession(ParticipantManager participantManager, int price, SessionStatus ready, RecruitmentStatus recruiting) {
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Enrolment enrolment = new Enrolment(participantManager, new Price(price), ready);
        // when
        Session session = new Session("TDD", sessionPeriod, enrolment, recruiting, null, null);
        return session;
    }

    @DisplayName("무료강의 생성")
    @Test
    void 무료강의생성() {
        // given
        Session session = createSession(new ParticipantManager(), 0, SessionStatus.READY, RecruitmentStatus.RECRUITING);
        // then
        assertThat(session.title()).isEqualTo("TDD");
        assertThat(session.isFree()).isTrue();
    }

    @DisplayName("유료강의 생성")
    @Test
    void 유료강의생성() {
        // given
        Session session = createSession(new ParticipantManager(10), 10000, SessionStatus.READY, RecruitmentStatus.RECRUITING);
        // then
        assertThat(session.title()).isEqualTo("TDD");
        assertThat(session.isFree()).isFalse();
    }

    @DisplayName("강의이미지 등록하여 강의 생성")
    @Test
    void 강의이미지_등록하여_강의_생성() {
        // given
        Session session = createSession(new ParticipantManager(10), 10000, SessionStatus.READY, RecruitmentStatus.RECRUITING);
        // when
        // then
        assertThat(session.title()).isEqualTo("TDD");
        assertThat(session.isFree()).isFalse();
    }

    @DisplayName("강의가 모집중일때 신청이 가능하다")
    @Test
    void 강의가_모집중일때_신청이_가능하다() {
        // given
        Session session = createSession(new ParticipantManager(1), 10000, SessionStatus.RECRUIT, RecruitmentStatus.RECRUITING);
        session.addParticipant(10000, new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.WAITING));
        // then
        assertThat(session.nowParticipants()).isEqualTo(1);
    }

    @DisplayName("강의가 모집중이 아닐때 신청이 불가능하다")
    @Test
    void 강의가_모집중이_아닐때_신청이_불가능하다() {
        // given
        Session session = createSession(new ParticipantManager(1), 10000, SessionStatus.RECRUIT, RecruitmentStatus.NOT_RECRUITING);
        // then
        assertThatThrownBy(() -> session.addParticipant(10000, new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.WAITING)))
                .isInstanceOf(NotRecruitingException.class);
    }

    @DisplayName("유료강의 결제")
    @Test
    void 유료강의결제() {
        // given
        Session session = createSession(new ParticipantManager(10), 10000, SessionStatus.RECRUIT, RecruitmentStatus.RECRUITING);
        session.addParticipant(10000, new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.WAITING));
        // then
        assertThat(session.nowParticipants()).isEqualTo(1);
    }

    @DisplayName("유료강의 참여가 최대를 넘기면 예외가 발생한다.")
    @Test
    void 유료강의_참여가_최대를_넘기면_예외가_발생한다() {
        // given
        Session session = createSession(new ParticipantManager(1), 10000, SessionStatus.RECRUIT, RecruitmentStatus.RECRUITING);
        session.addParticipant(10000, new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.WAITING));
        // then
        assertThatThrownBy(() -> session.addParticipant(10000, new SessionUserEnrolment(2L, 1L, ParticipantSelectionStatus.WAITING)))
                .isInstanceOf(MaxParticipantsException.class);
    }

    @DisplayName("유료강의 결제금액이 강의가격과 다르면 예외가 발생한다.")
    @Test
    void 유료강의_결제금액이_강의가격과_다르면_예외가_발생한다() {
        // given
        Session session = createSession(new ParticipantManager(10), 10000, SessionStatus.RECRUIT, RecruitmentStatus.RECRUITING);
        // then
        assertThatThrownBy(() -> session.addParticipant(5000, new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.WAITING)))
                .isInstanceOf(MissMatchPriceException.class);
    }
}
