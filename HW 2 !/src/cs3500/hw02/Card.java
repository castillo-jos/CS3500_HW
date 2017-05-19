package cs3500.hw02;

/**
 * Created by Gus on 5/17/2017.
 */
public class Card {
  private String suit;
  private String value;
  private static int cardIDCount = 0;
  private int cardID;

  public Card cardBehind = null;
  public Card cardInFront = null;

  public PileType cardIsInPile;
  public Card() {
  }

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
