package cs3500.hw02;

/**
 * Created by Gus on 5/17/2017.
 */
public class Card {
  private String suit;
  private String value;
  private static int cardIDCount = 0;
  private int cardID;
  private String data;

  public Card cardInFront = null;
  public Card cardInBack = null;

  public PileType cardIsInPile;

  /**
   * This is the default constructor.
   */
  public Card() {
  }

  /**
   * This builds the Card class, with suit and value and all.
   * @param suit
   * @param value
   */
  public Card(String suit, String value) {
    setSuit(suit);
    setValue(value);
    cardIDCount++;
    setCardID(cardIDCount);
  }

  public String getSuit() {
    return this.suit;
  }

  public String getValue() {
    return this.value;
  }

  public int getCardID() {
    return this.cardID;
  }

  public void setSuit(String suit) {
    this.suit = suit;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public void setCardID(int cardID) {
    this.cardID = cardID;
  }

  public String toString() {
    return getSuit() + getValue();
  }
}
