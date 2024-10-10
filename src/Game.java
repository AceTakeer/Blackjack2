import java.util.Scanner;

public class Game{
    static Scanner input = new Scanner(System.in);

    public static void gameStart(){
        System.out.println("Game Start");
        Player player = new Player();
        Player dealer = new Player();

        player.addCard();
        player.addCard();

        dealer.addCard();
        dealer.addCard();

        

        //Player Turn
        System.out.printf("Your score is currently %d. Enter 'H' to hit or 'S' to Stand!\n", player.getScore());
        char C = input.next().charAt(0);

        while(C != 'S'){
            player.addCard();
            
            if(player.score > 21){
                player.isBust = true;
                System.out.print("YOU BUSTED!!!!!\t");
                break;
            }

            if(player.score == 21){
                System.out.print("BLACKJACK!!!!\t");
                break;
            }

            System.out.printf("Your score is currently %d. Enter 'H' to hit or 'S' to Stand!\n", player.getScore());
            C = input.next().charAt(0);

        }

        //Dealers Turn
        while(dealer.getScore() < 17){
            dealer.addCard();
        }

        //Compare the two Scores

        if(dealer.getScore() > player.getScore() || player.isBust == true){
            System.out.printf("Dealer Score: %d\tPlayer Score: %d\tYOU LOSE!", dealer.getScore(),player.getScore());
        } else if(dealer.getScore() == player.getScore()) {
            System.out.printf("Dealer Score: %d\tPlayer Score: %d\tPUSH!",dealer.getScore(),player.getScore());
        } else {
            System.out.printf("Dealer Score: %d\tPlayer Score: %d\tYOU WIN!",dealer.getScore(),player.getScore());
        }

    }
    public static void main(String[] args) {
        gameStart();
    }


}

