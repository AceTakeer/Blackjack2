package PackageBlackjack;


public class Card {
    int cardValue;
    String cardType;
    String cardSymbol;

    public Card(int val, String typ, String sym){
        this.cardValue = val;
        this.cardType = typ;
        this.cardSymbol = sym; 

        switch(sym){
        case "C":
            this.cardSymbol = "Clubs";
            break;
         case "D":
            this.cardSymbol = "Diamonds";
            break;
         case "H":
            this.cardSymbol = "Hearts";
            break;
         case "S":
            this.cardSymbol = "Spades";
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
