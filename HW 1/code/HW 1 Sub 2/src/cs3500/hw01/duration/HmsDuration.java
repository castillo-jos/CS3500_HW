package cs3500.hw01.duration;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;

/**
 * Durations represented as hours, minutes, and seconds.
 */
public final class HmsDuration extends AbstractDuration {
  /**
   * Constructs a duration in terms of its length in hours, minutes, and
   * seconds.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @throws IllegalArgumentException if any argument is negative
   */
  public HmsDuration(int hours, int minutes, int seconds) {
    this(inSeconds(hours, minutes, seconds));
    ensureHms(hours, minutes, seconds);
  }

  /**
   * Constructs a duration in terms of its length in seconds.
   *
   * @param inSeconds the number of seconds (non-negative)
   * @throws IllegalArgumentException {@code inSeconds} is negative
   */
  public HmsDuration(long inSeconds) {
    if (inSeconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }

    seconds = secondsOf(inSeconds);
    minutes = minutesOf(inSeconds);
    hours = hoursOf(inSeconds);
  }

  private final int hours;
  private final int minutes;
  private final int seconds;

  @Override
  protected AbstractDuration fromSeconds(long seconds) {
    return new HmsDuration(seconds);
  }

  @Override
  public String format(String template) {
    String formattedTemplate = new String(template);
    for (int i = 0; i < formattedTemplate.length(); i++) {
      char pSymbol = formattedTemplate.charAt(i);
      if (pSymbol == '%') {
        char tSpecifier = formattedTemplate.charAt(i + 1);
        StringBuilder fSpecifier = new StringBuilder().append(pSymbol).append(tSpecifier);
        System.out.println(fSpecifier);

        if (fSpecifier.toString().equals("%h")) {
          System.out.print(i);
          formattedTemplate = formattedTemplate.substring(0, i)
                  + hours + formattedTemplate.substring(i + 2, formattedTemplate.length());
          System.out.println(formattedTemplate);
        } else if (fSpecifier.toString().equals("%m")) {
          System.out.print(i);
          formattedTemplate = formattedTemplate.substring(0, i)
                  + minutes + formattedTemplate.substring(i + 2, formattedTemplate.length());
          System.out.println(formattedTemplate);
        } else if (fSpecifier.toString().equals("%s")) {
          System.out.print(i);
          formattedTemplate = formattedTemplate.substring(0, i)
                  + seconds + formattedTemplate.substring(i + 2, formattedTemplate.length());
          System.out.println(formattedTemplate);
        } else if (fSpecifier.toString().equals("%t")) {
          formattedTemplate = formattedTemplate.substring(0, i)
                  + inSeconds() + formattedTemplate.substring(i + 2, formattedTemplate.length());
        } else if (fSpecifier.toString().equals("%H")) {
          NumberFormat formatter = new DecimalFormat("00");
          String pHours = formatter.format(hours);
          formattedTemplate = formattedTemplate.substring(0, i)
                  + pHours + formattedTemplate.substring(i + 2, formattedTemplate.length());
        } else if (fSpecifier.toString().equals("%M")) {
          NumberFormat formatter = new DecimalFormat("00");
          String pMinutes = formatter.format(minutes);
          formattedTemplate = formattedTemplate.substring(0, i)
                  + pMinutes + formattedTemplate.substring(i + 2, formattedTemplate.length());
        } else if (fSpecifier.toString().equals("%S")) {
          NumberFormat formatter = new DecimalFormat("00");
          String pSeconds = formatter.format(seconds);
          formattedTemplate = formattedTemplate.substring(0, i)
                  + pSeconds + formattedTemplate.substring(i + 2, formattedTemplate.length());
        } else if (fSpecifier.toString().equals("%%")) {
          formattedTemplate = formattedTemplate.substring(0, i)
                  + "%" + formattedTemplate.substring(i + 2, formattedTemplate.length());
        } else {
          return "Error: " + fSpecifier.toString() + " Format Specifier Not Recognized";
        }
      }
    }
    return formattedTemplate;
  }

  @Override
  public long inSeconds() {
    return inSeconds(hours, minutes, seconds);
  }

  @Override
  public String asHms() {
    return asHms(hours, minutes, seconds);
  }
}
