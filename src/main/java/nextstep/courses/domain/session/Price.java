package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

public class Price {

    private boolean isFree;
    private int money;

    private final ParticipantCount participantCount;

    public Price(boolean isFree, int money, ParticipantCount participantCount) {
        this.isFree = isFree;
        this.money = money;
        this.participantCount = participantCount;
    }

    public Price(boolean isFree, int money) {
        this.isFree = isFree;
        this.money = money;
        this.participantCount = null;
    }

    public boolean isFree() {
        return isFree;
    }

    public void addParticipant(int money, NsUser user) {
        validate(money);
        participantCount.add(user);
    }

    private void validate(int money) {
        if (!isFree) {
            participantCount.validateParticipant();
        }
        if (this.money != money) {
            throw new IllegalArgumentException("결제 금액이 다릅니다.");
        }
    }

    public int nowParticipants() {
        return participantCount.nowCount();
    }
}
