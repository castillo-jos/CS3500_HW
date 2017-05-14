package cs3500.hw01.duration;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static java.lang.Math.toIntExact;

/**
 * Durations represented compactly, with a range of 0 to
 * 2<sup>63</sup>-1 seconds.
 */
public final class CompactDuration extends AbstractDuration {
  /**
   * Constructs a duration in terms of its length in hours, minutes, and
   * seconds.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of inSeconds
   * @throws IllegalArgumentException if any argument is negative
   */
  public CompactDuration(int hours, int minutes, int seconds) {
    ensureHms(hours, minutes, seconds);
    this.inSeconds = inSeconds(hours, minutes, seconds);
  }

  /**
   * Constructs a duration in terms of its length in seconds.
   *
   * @param inSeconds the number of seconds (non-negative)
   * @throws IllegalArgumentException {@code inSeconds} is negative
   */
  public CompactDuration(long inSeconds) {
    if (inSeconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }

    this.inSeconds = inSeconds;
  }

  private final long inSeconds;

  @Override
  protected Duration fromSeconds(long seconds) {
    return new CompactDuration(seconds);
  }

  @Override
  public String format(String template) {
    System.out.println(inSeconds);
    int hours = toIntExact(inSeconds / 3600);
    System.out.println(inSeconds);
    long timeForMinutes = inSeconds - hours * 3600;
    System.out.println(inSeconds);
    int minutes = toIntExact(timeForMinutes / 60);
    System.out.println(inSeconds);
    int seconds = toIntExact(inSeconds - hours * 3600 - minutes * 60);
    System.out.println(inSeconds);
    String formattedTemplate = new String(template);
    for (int i = 0; i < formattedTemplate.length(); i++) {
      char pSymbol = formattedTemplate.charAt(i);
      if (pSymbol == '%') {
        char tSpecifier = formattedTemplate.charAt(i + 1);
        StringBuilder fSpecifier = new StringBuilder().append(pSymbol).append(tSpecifier);
        System.out.println(fSpecifier);

        if (fSpecifier.toString().equals("%h")) {
          formattedTemplate = formattedTemplate.substring(0, i) + hours
                  + formattedTemplate.substring(i + 2, formattedTemplate.length());
          System.out.println(formattedTemplate);
        } else if (fSpecifier.toString().equals("%m")) {
          System.out.print(i);
          formattedTemplate = formattedTemplate.substring(0, i) + minutes
                  + formattedTemplate.substring(i + 2, formattedTemplate.length());
          System.out.println(formattedTemplate);
        } else if (fSpecifier.toString().equals("%s")) {
          System.out.print(i);
          formattedTemplate = formattedTemplate.substring(0, i) + seconds
                  + formattedTemplate.substring(i + 2, formattedTemplate.length());
          System.out.println(formattedTemplate);
        } else if (fSpecifier.toString().equals("%t")) {
          formattedTemplate = formattedTemplate.substring(0, i) + inSeconds()
                  + formattedTemplate.substring(i + 2, formattedTemplate.length());
        } else if (fSpecifier.toString().equals("%H")) {
          NumberFormat formatter = new DecimalFormat("00");
          String pHours = formatter.format(hours);
          formattedTemplate = formattedTemplate.substring(0, i) + pHours
                  + formattedTemplate.substring(i + 2, formattedTemplate.length());
        } else if (fSpecifier.toString().equals("%M")) {
          NumberFormat formatter = new DecimalFormat("00");
          String pMinutes = formatter.format(minutes);
          formattedTemplate = formattedTemplate.substring(0, i) + pMinutes
                  + formattedTemplate.substring(i + 2, formattedTemplate.length());
        } else if (fSpecifier.toString().equals("%S")) {
          NumberFormat formatter = new DecimalFormat("00");
          String pSeconds = formatter.format(seconds);
          formattedTemplate = formattedTemplate.substring(0, i)
                  + pSeconds + formattedTemplate.substring(i + 2, formattedTemplate.length());
        } else if (fSpecifier.toString().equals("%%")) {
          formattedTemplate = formattedTemplate.substring(0, i)
                  + "%" + formattedTemplate.substring(i + 2, formattedTemplate.length());
        } else {
          return "Error: " + fSpecifier.toString() + "Format Specifier Not Recognized";
        }
      }
    }
    return formattedTemplate;
  }

  @Override
  public long inSeconds() {
    return inSeconds;
  }

  @Override
  public String asHms() {
    return String.format("%d:%02d:%02d",
            hoursOf(inSeconds),
            minutesOf(inSeconds),
            secondsOf(inSeconds));
  }
}