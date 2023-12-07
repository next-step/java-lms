package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

public class Enrolment {

    private final ParticipantManager participantManager;

    private final Price price;

    public Enrolment(ParticipantManager participantManager, Price price) {
        this.participantManager = participantManager;
        this.price = price;
    }

    public void addParticipant(int money, NsUser user) {
        validate(money);
        participantManager.add(user);
    }

    private void validate(int money) {
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

    public static Enrolment of(int maxParticipants, Price price) {
        return new Enrolment(ParticipantManager.of(maxParticipants), price);
    }
}
