package io.rainfall.generator.sequence;

import io.rainfall.utils.ConcurrentPseudoRandom;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Aurelien Broszniowski
 */
public class DistributionTest {

  @Test
  public void testGaussian() {
    Distribution distribution = Distribution.GAUSSIAN;
    final ConcurrentPseudoRandom rnd = new ConcurrentPseudoRandom();
    long min = Long.MAX_VALUE;
    long max = Long.MIN_VALUE;

    List<Long> nbs = new ArrayList<Long>();

    for (int i = 0; i < 5000; i++) {
      long next = distribution.generate(rnd, 0, 1000, 100);
      nbs.add(next);
      if (next < min) min = next;
      if (next > max) max = next;
    }

    Collections.sort(nbs);
    StringBuilder sb = new StringBuilder();
    for (Long nb : nbs) {
      sb.append(nb).append("\n");
    }
    System.out.println(sb.toString());
  }

  @Test
  public void testSecureGaussian() {
    final SecureRandom rnd = new SecureRandom();

    List<Long> nbs = new ArrayList<Long>();

    for (int i = 0; i < 5000; i++) {
      Double v = rnd.nextGaussian() * 150 + 500;
      long next = v.longValue();

      nbs.add(next);
    }

    Collections.sort(nbs);
    StringBuilder sb = new StringBuilder();
    for (Long nb : nbs) {
      sb.append(nb).append("\n");
    }
    System.out.println(sb.toString());
  }
}
