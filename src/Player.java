import java.util.Random;



public class Player{
    Random rand = new Random();

//Variables
    int deckSize;
    int[] deck = new int[50];
    int score;
    boolean isBust;

    public Player(){

        deckSize = 2;
        score = 0;

    }

//Gets
    public int[] getDeck(){
        return this.deck;
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
        
        deck[deckSize] = rand.nextInt(11) + 2;
        deckSize++;

    }

    public void reset(){
        for (int i: deck){
            deck[i] = 0;
        }
        deckSize = 2;
        isBust = false;
        score = 0;
    }






}