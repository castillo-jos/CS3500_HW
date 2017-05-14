package cs3500.hw01.duration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the format method of {@link Duration}s.
 * Add your tests to this class to assure that your format
 * method works properly
 */

public abstract class AbstractDurationFormatTest {
  @Test
  public void formatExample1() {
    assertEquals("4 hours, 0 minutes, and 9 seconds",
            hms(4, 0, 9)
                    .format("%h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void formatExample2() {
    assertEquals("4:05:17",
            hms(4, 5, 17).format("%h:%M:%S"));
  }

  // ADD MORE TESTS HERE
  // Your tests must only use hms(...) and sec(...) to construct new Durations
  // and must *not* directly say "new CompactDuration(...)" or
  // "new HmsDuration(...)"

  @Test
  public void formatExample3() {
    assertEquals("10800",
            hms(3, 0, 0).format("%t"));
  }

  @Test
  public void formatExample4() {
    assertEquals("0 hours, 10 minutes, and 9 seconds",
            sec(609)
                    .format("%h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void formatExample5() {
    assertEquals("3:00:00",
            sec(10800).format("%h:%M:%S"));
  }

  @Test
  public void formatExample6() {
    assertEquals("Give me a 110%",
            hms(1, 1, 1).format("Give me a 110%%"));
  }

  @Test
  public void formatExample8() {
    assertEquals("1001:09:00",
            hms(1000, 59, 600).format("%H:%M:%S"));
  }

  @Test
  public void formatExample15() {
    assertEquals("%%H",
            hms(100, 59, 60).format("%%%%H"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatExample9() {
    hms(1, 1, 1).format("%");
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatExample10() {
    hms(1, 1, 1).format("%%%");
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatExample11() {
    hms(1, 1, 1).format("%w");
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatExample12() {
    hms(1, 1, 1).format(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatExample13() {
    hms(1, 1, 1).format("% ");
  }

  @Test
  public void formatExample14() {
    assertEquals("00",
            hms(0, 600, 0).format("%M"));
  }

  @Test
  public void formatExample16() {
    assertEquals("00   00",
            hms(0, 600, 0).format("%M   %M"));
  }

  @Test
  public void formatExample17() {
    assertEquals("t",
            hms(0, 600, 0).format("t"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatExample18() {
    hms(1, 1, 1).format("%d");
  }

  @Test
  public void formatExample19() {
    assertEquals("%%",
            hms(0, 600, 0).format("%%%%"));
  }

  /*
    Leave this section alone: It contains two abstract methods to
    create Durations, and concrete implementations of this testing class
    will supply particular implementations of Duration to be used within 
    your tests.
   */

  /**
   * Constructs an instance of the class under test representing the duration
   * given in hours, minutes, and seconds.
   *
   * @param hours   the hours in the duration
   * @param minutes the minutes in the duration
   * @param seconds the seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration hms(int hours, int minutes, int seconds);

  /**
   * Constructs an instance of the class under test representing the duration
   * given in seconds.
   *
   * @param inSeconds the total seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration sec(long inSeconds);

  public static final class HmsDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new HmsDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new HmsDuration(inSeconds);
    }
  }

  public static final class CompactDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new CompactDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new CompactDuration(inSeconds);
    }
  }
}
