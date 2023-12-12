package nextstep.courses.domain.session;

import nextstep.courses.domain.participant.ParticipantManager;
import nextstep.courses.domain.participant.SessionParticipants;
import nextstep.courses.domain.participant.SessionUserEnrolment;
import nextstep.courses.type.SessionStatus;

public class Enrolment {

    private final ParticipantManager participantManager;

    private final Price price;

    private SessionStatus status;

    public Enrolment(ParticipantManager participantManager, Price price, SessionStatus status) {
        this.participantManager = participantManager;
        this.price = price;
        this.status = status;
    }

    public void addParticipant(int money, SessionUserEnrolment user) {
        validate(money);
        participantManager.add(user);
    }

    private void validate(int money) {
        if (status.equals(status.FINISH)) {
            throw new IllegalArgumentException("종료된 강의 입니다.");
        }
        if (!price.isFree()) {
            participantManager.validateParticipant();
        }
        if (price.money() != money) {
            throw new IllegalArgumentException("결제 금액이 다릅니다.");
        }
    }


    public int nowParticipants() {
        return participantManager.nowCount();
    }

    public int maxParticipants() {
        return participantManager.max();
    }

    public boolean isFree() {
        return price.isFree();
    }

    public int price() {
        return price.money();
    }

    public String status() {
        return status.toString();
    }

    public static Enrolment of(int maxParticipants, Price price, SessionStatus status) {
        return new Enrolment(ParticipantManager.of(maxParticipants), price, status);
    }

    public void mappaedBySessionParticipants(SessionParticipants sessionParticipants) {
        this.participantManager.mapppadBySessionParticipants(sessionParticipants);
    }
}
