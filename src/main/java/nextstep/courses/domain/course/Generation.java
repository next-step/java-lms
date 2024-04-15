package nextstep.courses.domain.course;

public class Generation {

  public static final String NEGATIVE_NUMBER_OR_ZERO_IS_NOT_ALLOWED = "기수 값은 0이나 음수는 허용되지 않습니다. input: %s";

  private Long id;

  public Generation(Long id) {
    validate(id);
    this.id = id;
  }

  private void validate(Long number) {
    if (number <= 0) {
      throw new IllegalArgumentException(String.format(NEGATIVE_NUMBER_OR_ZERO_IS_NOT_ALLOWED, number));
    }
  }

  public boolean isSame(Long id) {
    return this.id.equals(id);
  }
}
