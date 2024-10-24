package PackageBlackjack;


import java.util.ArrayList;
import java.util.Random;


public class testplayer{
    static Random rand = new Random();

//Variables
    ArrayList<Card> playerDeck = new ArrayList<Card>();
    int score;
    boolean isBust;
    boolean hasAce;
    boolean hasUsedAce;

    public testplayer(){
        score = 0;
    }

//Gets

    public ArrayList<Card> getPlayerDeck() {
        return playerDeck;
    }
    
    public int getScore(){
        return score;
    }

    public boolean getBust(){
        return isBust;
    }
//Sets
    public void setScore(int ns){
        score+=ns;
    }
    public void setBust(boolean nb){
        isBust = nb;
    }

    
    public void addAce(String s) {
    	
    	Card newAce = new Card(s, "Ace");
    	
    	playerDeck.add(newAce);
    	
    	if(newAce.getCardType().equals("Ace")) {
        	hasAce = true;
        }
    	
    	
        
    	
        if( (score + newAce.cardValue) > 21 && this.hasUsedAce == false) {
        	
        	System.out.printf("Before Code Ran: %d", this.score);
        	
        	for (Card i : playerDeck) {
        		
        		System.out.print("\n\n");
        		System.out.println(i.getCardType());
        		
        		  if(i.cardType.equals("Ace")) {
        			  System.out.println("\n\nAce Transformed!\n\n");
        			  
        			  i.cardValue = 1;
        			  this.score -= 10;
        			  hasUsedAce = true;
        			  break;
        		  }
        		  
        		  
        	}
        	
        	
        	
        	
        }
        
        
        if((newAce.getCardType().equals("Ace") && this.score >= 11)) {
        	System.out.println("Yessir");
        	newAce.cardValue -= 10;
        } 

        
        
        System.out.printf("\n\n After code ran: %d \n\n", this.score);
        this.score += newAce.cardValue;
       
        if(score > 21) {
        	isBust = true;
        }
    }

    
    
    public void addCard(String s, String r){

        
        
        Card newCard = new Card(s,r);
        
        
        playerDeck.add(newCard);
        
        if(newCard.getCardValue() == 11) {
        	hasAce = true;
        }
        
        if(hasUsedAce == true && newCard.cardValue == 11) {
        	newCard.cardValue = 1;
        }
        
        if( (score + newCard.getCardValue()) >= 22 && hasUsedAce == false) {
        	
        	for (Card i : playerDeck) {
        		
        		  if(i.cardValue == 11) {
        			  i.cardValue = 1;
        			  score -= 10;
        			  break;
        		  }
        		  
        	}
        	hasUsedAce = true;
        }
        
        score += newCard.cardValue;
       
        if(score > 21) {
        	isBust = true;
        }
        
    }

    public void reset(){
        
        isBust = false;
        hasAce = false;
        hasUsedAce = false;
        score = 0;
        playerDeck.clear();

    }

    public void setPlayerDeck(ArrayList<Card> newPlayerDeck) {
        playerDeck = newPlayerDeck;
    }


}