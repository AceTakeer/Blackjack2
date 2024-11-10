package PackageBlackjack;

import java.util.Random;
import java.util.ArrayList;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class BlackjackController {

	Random rand = new Random();
	
	//Preparing the playing card deck for use
	ArrayList<Card> allPlayingCards = new ArrayList<Card>();
	final String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    final String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    
    private static final int cardWidth = 100;
    private static final int cardHeight = 140;
 
	
	Player player = new Player();
    Player dealer = new Player();
    
    int wins = 0;
    boolean giveUp = false;
    int betPool = 0;
    
    
    @FXML
    private Label moneyDisplay;
    
    
   
    
    @FXML
    private Label dealerTotal;

    @FXML
    private Button giveUpButton;

    @FXML
    private Button hitButton;
    
    @FXML
    private Label playerTotal;

    @FXML
    private HBox playerVisualDeck;
    
    @FXML
    private HBox dealerVisualDeck;
    
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
    private Text cardInfoDisplay;
    
    @FXML
    private Pane backgroundColorPane;
    
    @FXML
    private TextField betTextField;

    @FXML
    private Button betEnter;
    
    
    public void initialize() {
  	   moneyDisplay.setText(String.valueOf(player.getMoney()));
     }
    
    
    @FXML
    void betEnterPressed(ActionEvent event) {
    	System.out.println(player.getMoney());
    	player.money -= Integer.parseInt(betTextField.getText());
    	betPool += Integer.parseInt(betTextField.getText());
    	moneyDisplay.setText(String.valueOf(player.getMoney()));
    	betEnter.setVisible(false);
    	betTextField.setVisible(false);
    	gameStart();
    }
   


    @FXML
    void giveUpButtonPressed(ActionEvent event) {
    	giveUp = true;
    	gameEnd();
    }

    @FXML
    void hitButtonPressed(ActionEvent event) {
    		
    	//Grabs a random card from the main deck
    	int playerNewCardIndex = rand.nextInt(allPlayingCards.size()-1);
		Card playerNewCard = allPlayingCards.get(playerNewCardIndex);
		allPlayingCards.remove(playerNewCardIndex);
		
		
		//Adds the card to your deck
		player.addCard(playerNewCard.cardSymbol,playerNewCard.cardType);
		addCardImage(playerVisualDeck, playerNewCard);
    	
		//Displays what card you pulled in both UI and console
    	//cardInfoDisplay.setText( "You pulled a " + playerNewCard.getCardType() + " of " + playerNewCard.getCardSymbol() );
		
    	System.out.printf("\n%s %s %d\n",playerNewCard.getCardType(),playerNewCard.getCardSymbol(),player.getScore());
    	
    	//Changes player's score in UI
    	playerTotal.setText(String.valueOf(player.getScore()));
    	
    	//Game will stop if player hits a blackjack
    	if(player.getScore() == 21) {
    		dealerTurn();
    	}
    	//Game will stop if player busts (score higher than 21)
    	else if(player.getBust() == true) {
    		gameEnd();
    	}
    }

    @FXML
    void standButtonPressed(ActionEvent event) {
    	hitButton.setVisible(false);
    	giveUpButton.setVisible(false);
    	standButton.setVisible(false);
    	dealerTurn();
    }

    @FXML
    void startButtonPressed(ActionEvent event) {
    	//Hides 'title' and 'start' button
    	startButton.setVisible(false);
    	gameTitleText.setVisible(false);
    	
    	
    	betTextField.setVisible(true);
    	betEnter.setVisible(true);
    	
    }

    public void gameEnd() {
    	//Resets booleans
    	boolean didWin = false;
    	boolean didPush = false;
    	
    	//hides buttons
    	hitButton.setVisible(false);
    	standButton.setVisible(false);
    	giveUpButton.setVisible(false);
    	
    	//Re-shows the 'start' button and 'title'
    	startButton.setVisible(true);
    	gameTitleText.setVisible(true);
    	
    	//Game Result Logic
    	if(giveUp == true) {
    		;// skips everything else if give up button is pressed
    		
    	} else if( (dealer.getScore() == player.getScore()) && (player.getBust() == false && dealer.getBust() == false) ){ // TIE
    		
    		didPush = true;
         
        } else if ( (dealer.getScore() > player.getScore() && dealer.getBust() == false) || player.getBust() == true) { //DEALER WIN

        	didWin = false;
            
        } else if ( (dealer.getScore() < player.getScore() && player.getBust() == false) || dealer.getBust() == true) { //PLAYER WIN

        	didWin = true;
        	wins++;
        	winTotal.setText(String.valueOf(wins));
        
        }
    	
    	//'Title' text displays different message depending on the results
    	if(giveUp == true) {
    		didWin = false;
    		gameTitleText.setText("You gave up. . . . Play Again?");
    		} else if(didPush == true) {
    		gameTitleText.setText("You pushed/Tied! Play Again?");
    			player.money += betPool;
    		} else if(didWin == false) {
        		gameTitleText.setText("You lose! Play Again?");
        	} else {
        		gameTitleText.setText("You Win! Play Again?");
        		player.money += betPool * 2;
        	}
    	
    	moneyDisplay.setText(String.valueOf(player.getMoney()));
    	//'start' button text changes
    	startButton.setText("PLAY AGAIN");
    	betPool = 0;
    }
    
    public void dealerTurn() {
    	//Dealer continues to pull cards until their score is greater than or equal to 17
    	while(dealer.getScore() < 17) {
    		
    		int dealerNewCardIndex = rand.nextInt(allPlayingCards.size()-1);
    		Card dealerNewCard = allPlayingCards.get(dealerNewCardIndex);
    		allPlayingCards.remove(dealerNewCardIndex);
    		
    		dealer.addCard(dealerNewCard.cardSymbol, dealerNewCard.cardType);
    		addCardImage(dealerVisualDeck,dealerNewCard);
    		
    		dealerTotal.setText(String.valueOf(dealer.getScore()));
    	}
    	
    	//Dealer busts if their score is higher than 21
    	if(dealer.getScore() > 21) {
    		dealer.isBust = true;
    	}
    	
    	//Game end function called
    	gameEnd();
    }




    public void gameStart(){
    	
    	hitButton.setVisible(true);
    	giveUpButton.setVisible(true);
    	standButton.setVisible(true);
    	
    	allPlayingCards.clear();
    	
    	playerVisualDeck.getChildren().clear();
    	dealerVisualDeck.getChildren().clear();
    	
    	//Initializes the playing card deck
    	for (int i = 0; i < suits.length; i++) {
    		
      	  for (int j = 0; j < ranks.length;j++) {
      		  
      		Card playingCard = new Card(suits[i],ranks[j]);
      		allPlayingCards.add(playingCard);
      	  }
      	  
      	}
        
        System.out.println("\nGame Start\n\n\n\n");
        
        cardInfoDisplay.setText(" ");
        giveUp = false;
        player.reset();
        dealer.reset();
        
        //Players first two cards
        int playerFirstCardIndex = rand.nextInt(allPlayingCards.size()-1);
        int playerSecondCardIndex = rand.nextInt(allPlayingCards.size()-1);
        
        Card playerFirstCard = allPlayingCards.get(playerFirstCardIndex);
        allPlayingCards.remove(playerFirstCardIndex);
        Card playerSecondCard = allPlayingCards.get(playerSecondCardIndex);
        allPlayingCards.remove(playerSecondCardIndex);
        
        player.addCard(playerFirstCard.cardSymbol,playerFirstCard.cardType);
		player.addCard(playerSecondCard.cardSymbol,playerSecondCard.cardType);
        
		addCardImage(playerVisualDeck,playerFirstCard);
		addCardImage(playerVisualDeck,playerSecondCard);
		
        //Dealers First Two Cards
        int dealerFirstCardIndex = rand.nextInt(allPlayingCards.size()-1);
        int dealerSecondCardIndex = rand.nextInt(allPlayingCards.size()-1);
        
        Card dealerFirstCard = allPlayingCards.get(dealerFirstCardIndex);
        allPlayingCards.remove(dealerFirstCardIndex);
        Card dealerSecondCard = allPlayingCards.get(dealerSecondCardIndex);
        allPlayingCards.remove(dealerSecondCardIndex);
        
        
		dealer.addCard(dealerFirstCard.cardSymbol,dealerFirstCard.cardType);
		dealer.addCard(dealerSecondCard.cardSymbol,dealerSecondCard.cardType);
		
		addCardImage(dealerVisualDeck,dealerFirstCard);
		addCardImage(dealerVisualDeck,dealerSecondCard);
		
		
		//Console print of player's cards
		System.out.printf("\n%s %s\n",playerFirstCard.getCardType(),playerFirstCard.getCardSymbol());
		System.out.printf("\n%s %s\n",playerSecondCard.getCardType(),playerSecondCard.getCardSymbol());
		System.out.printf("\n%d\n", player.getScore());
	
        //Shows both dealer and player scores
        playerTotal.setText(String.valueOf(player.score));
        dealerTotal.setText(String.valueOf(dealer.score));
        
        //Game will stop if player hits a blackjack
        if(player.getScore() == 21) {
    		dealerTurn();
    	}
        //Game will stop if player busts automatically at the start (NOTE: it is not actually possible to have a starting hand of more than 21. This is more of an exception handler than anything.
    	else if(player.getBust() == true) {
    		gameEnd();
    	}

    }
    
    public void addCardImage(HBox d, Card c) {
    	ImageView newCardPicTest = new ImageView();
		newCardPicTest.setFitHeight(cardHeight);
		newCardPicTest.setFitWidth(cardWidth);
		
		newCardPicTest.setImage(c.cardImage);
		d.getChildren().add(newCardPicTest);
    }
       
   

}
    

