package cs3500.hw02;

/**
 * Created by Gus on 5/17/2017.
 */

/**
 * For assignment 4, I made several changes to my Card class, as it could be condensed, and still
 * meet assignment requirements. In previous assignments, Card had a static field that counted the
 * number of Card classes being instantiated. Card also had Card fields that were filled with any
 * other card placed either behind or in front of it. This was to create a sort of "linked lists"
 * with cards. These have all been removed.
 */
public class Card {
  private String suit;
  private String value;
  /**
   * PileType has been set to private.
   */
  private PileType pileType;

  /**
   * This builds the Card class, with suit and value and all.
   *
   * @param suit  the Suit of the card.
   * @param value the value of card.
   */
  public Card(String suit, String value) {
    setSuit(suit);
    setValue(value);
  }

  public String getSuit() {
    return this.suit;
  }

  public String getValue() {
    return this.value;
  }


  public void setSuit(String suit) {
    this.suit = suit;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String toString() {
    return getValue() + getSuit();
  }

  /**
   * An accessor method for PileType has been created in order to set this Card's PileType field.
   *
   * @param pileType Type of pile card is in.
   */
  public void setPileType(PileType pileType) {
    this.pileType = pileType;
  }

  /**
   * An accessor method for PileType has been created in order to get this Card's PileType field.
   *
   * @return This returns pileType.
   */

  public PileType getPileType() {
    return pileType;
  }
}
