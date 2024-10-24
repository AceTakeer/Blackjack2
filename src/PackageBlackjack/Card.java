package PackageBlackjack;

import java.util.Random;

public class Card {
    int cardValue;
    String cardType;
    String cardSymbol;
    Random rand = new Random();
    
    public Card(String suit, String rank){
    	
        this.cardType = rank;
        this.cardSymbol = suit; 

        
        
        if( this.cardType.equals("Jack") || this.cardType.equals("King") || this.cardType.equals("Queen")) {
        	
        	this.cardValue = 10;
        	
        } else if (this.cardType.equals("Ace")) {
        	this.cardValue = 11;
        	
        } else {
        	
        	this.cardValue = Integer.valueOf(rank);
        	
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
