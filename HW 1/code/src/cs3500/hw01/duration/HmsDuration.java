package cs3500.hw01.duration;

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
    if (template == null) {
      throw new IllegalArgumentException();
    }
    String formattedTemplate = new String(template);
    for (int i = 0; i < formattedTemplate.length(); i++) {
      char pSymbol = formattedTemplate.charAt(i);
      if (pSymbol == '%' && i < formattedTemplate.length() - 1) {
        char tSpecifier = formattedTemplate.charAt(i + 1);
        StringBuilder fSpecifier = new StringBuilder().append(pSymbol).append(tSpecifier);

        switch (fSpecifier.toString()) {
          case "%h":
            formattedTemplate = formattedTemplate.substring(0, i)
                    + hours + formattedTemplate.substring(i + 2, formattedTemplate.length());
            break;
          case "%m":
            formattedTemplate = formattedTemplate.substring(0, i)
                    + minutes + formattedTemplate.substring(i + 2, formattedTemplate.length());
            break;
          case "%s":
            formattedTemplate = formattedTemplate.substring(0, i)
                    + seconds + formattedTemplate.substring(i + 2, formattedTemplate.length());
            break;
          case "%t":
            formattedTemplate = formattedTemplate.substring(0, i)
                    + inSeconds() + formattedTemplate.substring(i + 2, formattedTemplate.length());
            break;
          case "%H":
            String pHours = String.format("%02d", hours);
            formattedTemplate = formattedTemplate.substring(0, i)
                    + pHours + formattedTemplate.substring(i + 2, formattedTemplate.length());
            break;
          case "%M":
            String pMinutes = String.format("%02d", minutes);
            formattedTemplate = formattedTemplate.substring(0, i)
                    + pMinutes + formattedTemplate.substring(i + 2, formattedTemplate.length());
            break;
          case "%S":
            String pSeconds = String.format("%02d", seconds);
            formattedTemplate = formattedTemplate.substring(0, i)
                    + pSeconds + formattedTemplate.substring(i + 2, formattedTemplate.length());
            break;
          case "%%":
            formattedTemplate = formattedTemplate.substring(0, i)
                    + "%" + formattedTemplate.substring(i + 2,
                    formattedTemplate.length());
            break;
          default:
            throw new IllegalArgumentException();
        }
      } else if (pSymbol == '%' && i >= formattedTemplate.length() - 1) {
        throw new IllegalArgumentException();
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
