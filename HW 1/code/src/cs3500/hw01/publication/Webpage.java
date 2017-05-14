package cs3500.hw01.publication;

/**
 * Created by Gus on 5/12/2017.
 */

/**
 * Represents bibliographic information for webpages.
 */
public class Webpage implements Publication {
  private final String title;
  private final String url;
  private final String dataDownloaded;

  /**
   * Constructs a {@code Webpage} object.
   *
   * @param title          the title of the Webpage
   * @param url            the url of the Webpage
   * @param dataDownloaded the date when Webpage accessed
   */
  public Webpage(String title, String url, String dataDownloaded) {
    this.title = title;
    this.url = url;
    this.dataDownloaded = dataDownloaded;
  }

  @Override
  public String citeApa() {
    return "" + title + ". Retrieved " + dataDownloaded + ", from " + url + ".";
  }

  @Override
  public String citeMla() {
    return "\"" + title + ".\" Web. " + dataDownloaded + " <" + url + ">.";
  }
}
