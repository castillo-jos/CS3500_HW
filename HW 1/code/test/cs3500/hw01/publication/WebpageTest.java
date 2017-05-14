package cs3500.hw01.publication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Gus on 5/12/2017.
 */
public class WebpageTest {
  Publication buzzfeed = new Webpage("The 25 Most Epic Cat Beards Of All Time",
          "http://www.buzzfeed.com/summeranne/"
                  + "the-25-most-epic-cat-beards-of-all-time",
          "Fri, 15 May 2015 08:15:49 -0400");

  @Test
  public void testCiteApa() {
    assertEquals("The 25 Most Epic Cat Beards Of All Time. "
                    + "Retrieved Fri, 15 May 2015 08:15:49 -0400"
                    + ", "
                    + "from http://www.buzzfeed.com/summeranne/the-25-most-epic-cat-beards-of-all-time.",
            buzzfeed.citeApa());
  }

  @Test
  public void testCiteMla() {
    assertEquals("\"The 25 Most Epic Cat Beards Of All Time.\" Web.Fri, 15 May 2015 08:15:49 -0400<http://www.buzzfeed.com/summeranne/the-25-most-epic-cat-beards-of-all-time>.",
            buzzfeed.citeMla());
  }
}