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
        payCheck(money);
        participantCount.add(user);
    }

    private void payCheck(int money) {
        if (isFree) {
            throw new IllegalArgumentException("무료 강의는 결제할 수 없습니다.");
        }
        if(this.money != money) {
            throw new IllegalArgumentException("결제 금액이 다릅니다.");
        }
    }

    public int nowParticipants() {
        return participantCount.nowCount();
    }
}
