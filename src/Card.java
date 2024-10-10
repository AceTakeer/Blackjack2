public class Card {
    int cardValue;
    String cardType;
    String cardSymbol;

    public Card(int val, String typ, String sym){
        this.cardValue = val;
        this.cardType = typ;
        this.cardSymbol = sym; 

        switch(sym){

            case "H" -> this.cardSymbol = "Hearts";

            case "S" -> this.cardSymbol = "Spades";

            case "C" -> this.cardSymbol = "Clubs";

            case "D" -> this.cardSymbol = "Diamonds";
        }
    }

    public int getCardValue() {
        return this.cardValue;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardSymbol() {
        return cardSymbol;
    }
}
