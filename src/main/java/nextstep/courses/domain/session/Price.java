package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

public class Price {

    private boolean isFree;
    private int money;

    private final ParticipantManager participantManager;

    public Price(boolean isFree, int money, ParticipantManager participantManager) {
        this.isFree = isFree;
        this.money = money;
        this.participantManager = participantManager;
    }

    public Price(boolean isFree, int money) {
        this.isFree = isFree;
        this.money = money;
        this.participantManager = null;
    }

    public boolean isFree() {
        return isFree;
    }

    public void addParticipant(int money, NsUser user) {
        validate(money);
        participantManager.add(user);
    }

    private void validate(int money) {
        if (!isFree) {
            participantManager.validateParticipant();
        }
        if (this.money != money) {
            throw new IllegalArgumentException("결제 금액이 다릅니다.");
        }
    }

    public int nowParticipants() {
        return participantManager.nowCount();
    }
}
