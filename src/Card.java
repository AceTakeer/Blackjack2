public class Card {
    int cardValue;
    String cardType;
    String cardSymbol;

    public Card(int val, String typ, String sym){
        this.cardValue = val;
        this.cardType = typ; 

        switch(sym){

            case "H" -> this.cardSymbol = "Hearts";

            case "S" -> this.cardSymbol = "Spades";

            case "C" -> this.cardSymbol = "Clubs";

            case "D" -> this.cardSymbol = "Diamonds";

        }
    }
}
