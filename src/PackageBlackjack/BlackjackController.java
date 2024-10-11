package PackageBlackjack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BlackjackController {

	Player player = new Player();
    Player dealer = new Player();
    
    int wins = 0;
    
    @FXML
    private Label dealerTotal;

    @FXML
    private Button giveUpButton;

    @FXML
    private Button hitButton;
    
    @FXML
    private Label playerTotal;

    @FXML
    private Button standButton;

    @FXML
    private Button startButton;

    @FXML
    private Label winTotalCounter;
    
    @FXML
    private Label gameTitleText;
    
    @FXML
    private Label winTotal;

    @FXML
    void giveUpButtonPressed(ActionEvent event) {

    }

    @FXML
    void hitButtonPressed(ActionEvent event) {
    	player.addCard();
    	playerTotal.setText(String.valueOf(player.getScore()));
    	
    	if(player.getScore() == 21) {
    		dealerTurn();
    	}
    	else if(player.getBust() == true) {
    		gameEnd();
    	}
    }

    @FXML
    void standButtonPressed(ActionEvent event) {
    	hitButton.setVisible(false);
    	standButton.setVisible(false);
    	dealerTurn();
    }

    @FXML
    void startButtonPressed(ActionEvent event) {
    	startButton.setVisible(false);
    	gameTitleText.setVisible(false);
    	hitButton.setVisible(true);
    	standButton.setVisible(true);
    	gameStart();
    	
    }

    public void gameEnd() {
    	boolean didWin = false;
    	boolean didPush = false;
    	hitButton.setVisible(false);
    	standButton.setVisible(false);
    	
    	startButton.setVisible(true);
    	gameTitleText.setVisible(true);
    	
    	//Game Result Logic
    	
    	if( (dealer.getScore() == player.getScore()) && (player.getBust() == false && dealer.getBust() == false) ){ // tie
    		
    		didPush = true;
            //System.out.printf("Dealer Score: %d\tPlayer Score: %d\tPUSH!",dealer.getScore(),player.getScore());

        } else if ( (dealer.getScore() > player.getScore() && dealer.getBust() == false) || player.getBust() == true) { //Dealer win

        	didWin = false;
            //System.out.printf("Dealer Score: %d\tPlayer Score: %d\tYOU LOSE!", dealer.getScore(),player.getScore());

        } else if ( (dealer.getScore() < player.getScore() && player.getBust() == false) || dealer.getBust() == true) { //player win

        	didWin = true;
        	wins++;
        	winTotal.setText(String.valueOf(wins));
        	
            //System.out.printf("Dealer Score: %d\tPlayer Score: %d\tYOU WIN!",dealer.getScore(),player.getScore());

        }
    	
    	//
    	if(didPush == true) {
    		gameTitleText.setText("You pushed/Tied! Play Again?");
    	} else {
    		if(didWin == false) {
        		gameTitleText.setText("You lose! Play Again?");
        	} else {
        		gameTitleText.setText("You Win! Play Again?");
        	}
    	}
    	
    
    	startButton.setText("PLAY AGAIN");
    }
    
    public void dealerTurn() {
    	while(dealer.getScore() < 17) {
    		dealer.addCard();
    		dealerTotal.setText(String.valueOf(dealer.getScore()));
    	}
    	
    	if(dealer.getScore() > 21) {
    		dealer.isBust = true;
    	}
    	gameEnd();
    }




    public void gameStart(){
        
        System.out.println("Game Start");
        
       
        player.reset();
        dealer.reset();

        player.addCard();
        player.addCard();
        playerTotal.setText(String.valueOf(player.getScore()));
        
        dealer.addCard();
        dealer.addCard();
        dealerTotal.setText(String.valueOf(dealer.getScore()));

    }
       

    }
    

