package nextstep.courses.domain.session;

public class ParticipantCount {

    private int maxParticipants;
    private int nowParticipants;

    public ParticipantCount(int maxParticipants) {
        this(maxParticipants, 0);
    }

    public ParticipantCount(int maxParticipants, int nowParticipants) {
        validateParticipant(maxParticipants, nowParticipants);
        this.maxParticipants = maxParticipants;
        this.nowParticipants = nowParticipants;
    }

    public void add() {
        nowParticipants++;
        validateParticipant(maxParticipants, nowParticipants);
    }

    private void validateParticipant(int maxParticipants, int nowParticipants) {
        if (maxParticipants < nowParticipants) {
            throw new IllegalArgumentException("최대 참가자 수를 초과하였습니다.");
        }
    }

    public int max() {
        return maxParticipants;
    }

    public int nowCount() {
        return nowParticipants;
    }
}
