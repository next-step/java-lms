package nextstep.qna.domain.generator;

import java.util.Random;

public class RandomIdGenerator {

  public static final Random RANDOM = new Random();

  public static long generate() {
    return RANDOM.nextLong();
  }
}
