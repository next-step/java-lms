package nextstep.courses.domain.session.policy.tuition;

public interface TuitionPolicy {
  void validate(long payAmount);
}