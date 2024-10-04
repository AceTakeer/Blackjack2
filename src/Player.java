import java.util.Random;
import java.util.ArrayList;


public class Player{
    Random rand = new Random();

//Variables
    ArrayList<Card> playerDeck = new ArrayList<Card>();
    int score;
    boolean isBust;

    public Player(){
        score = 0;
    }

//Gets

    public ArrayList<Card> getPlayerDeck() {
        return playerDeck;
    }
    
    public int getScore(){
        return this.score;
    }

    public boolean getBust(){
        return this.isBust;
    }
//Sets
    public void setScore(int ns){
        this.score+=ns;
    }
    public void setBust(boolean nb){
        this.isBust = nb;
    }

    public void addCard(){

        int[] values = {1,2,3,4,5,6,7,8,9,10,11};
        String[] cardSign = {"A","1","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] symbol = {"H","S","C","D"};
        
        Card newCard = new Card(values[rand.nextInt(values.length)],cardSign[rand.nextInt(values.length)],symbol[rand.nextInt(symbol.length)]);
        playerDeck.add(newCard);
        this.score += newCard.cardValue;
    }

    public void reset(){
        
        isBust = false;
        score = 0;
        playerDeck.clear();

    }


}