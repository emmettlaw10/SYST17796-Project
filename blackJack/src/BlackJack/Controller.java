/**
 * the controller for the main game screen
 *
 * @author Emmett Law Aug 2021
 */

package BlackJack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.management.StandardEmitterMBean;
import java.util.ArrayList;

public class Controller {
    //is it because this is blank
    @FXML
    Button bet;
    @FXML
    Button hit;
    @FXML
    Button stand;
    @FXML
    Button doubleDown;
    @FXML
    Button restart;
    @FXML
    Button insurance;
    @FXML
    ImageView hide;
    @FXML
    ImageView p1;
    @FXML
    ImageView p2;
    @FXML
    ImageView p3;
    @FXML
    ImageView p4;
    @FXML
    ImageView p5;
    @FXML
    ImageView d1;
    @FXML
    ImageView d2;
    @FXML
    ImageView d3;
    @FXML
    ImageView d4;
    @FXML
    ImageView d5;
    @FXML
    Label balance;
    @FXML
    Label message;
    @FXML
    Label pScore;
    @FXML
    Label dScore;
    @FXML
    TextField betInput;


    Stage primaryStage;
    Pane AnchorPane;

    Shoe gameShoe = new Shoe(6);
    Player player = new Player();
    Dealer dealer = new Dealer();
    int betAmount;

    /**
     * function to deal initial cards
     */

    public void deal () {
        for (int i = 0; i<2; i++) {
            player.playerHand.getCard(gameShoe.dealCard());
            dealer.dealerHand.getCard(gameShoe.dealCard());
        }
    }
    /**
     *Function to update pictures to represent players cards
     */
    public void playerCardPics(){
        for (int i =0; i<player.playerHand.size(); i++) {
            if (i==0){
                Image a  = new Image(getClass().getResourceAsStream(player.playerHand.assignPic(i)));
                p1.setImage(a);
            }
            if (i==1){
                Image b  = new Image(getClass().getResourceAsStream(player.playerHand.assignPic(i)));
                p2.setImage(b);
            }

            if (i==2){
                Image c  = new Image(getClass().getResourceAsStream(player.playerHand.assignPic(i)));
                p3.setImage(c);
            }
            if (i==3){
                Image d  = new Image(getClass().getResourceAsStream(player.playerHand.assignPic(i)));
                p4.setImage(d);
            }
            if (i==4){
                Image e  = new Image(getClass().getResourceAsStream(player.playerHand.assignPic(i)));
                p5.setImage(e);
            }
        }
    }
    /**
     *function to update pictures to represent dealers cards
     */
    public void dealerCardPics(){
        for (int i =0; i<dealer.dealerHand.size(); i++) {
            if (i==0){
                Image f  = new Image(getClass().getResourceAsStream(dealer.dealerHand.assignPic(i)));
                d1.setImage(f);
            }
            if (i==1){
                Image g  = new Image(getClass().getResourceAsStream(dealer.dealerHand.assignPic(i)));
                d2.setImage(g);
            }

            if (i==2){
                Image h  = new Image(getClass().getResourceAsStream(dealer.dealerHand.assignPic(i)));
                d3.setImage(h);
            }
            if (i==3){
                Image j  = new Image(getClass().getResourceAsStream(dealer.dealerHand.assignPic(i)));
                d4.setImage(j);
            }
            if (i==4){
                Image k  = new Image(getClass().getResourceAsStream(dealer.dealerHand.assignPic(i)));
                d5.setImage(k);
            }
        }
    }
    /**
     *function to take players initial bet value. Also deals cards, checks and sets initial hand values, and updates player balance.
     *
     */
    public void bet(){
        try{
            message.setText("It is your turn");
            if (Integer.parseInt(betInput.getText()) <= player.getBalance()) {
                betAmount = Integer.parseInt(betInput.getText());
            }
            else {
                message.setText("Invalid bet. Game started with bet of 0");
            }

            hide.setOpacity(100);
            d2.setOpacity(0);
            balance.setText(String.valueOf(player.getBalance() - betAmount));
            player.setBalance(player.getBalance() - betAmount);
            deal();
            if (dealer.dealerHand.checkNatural() || player.playerHand.checkNatural()) {
                endGame();
            }
            playerCardPics();
            dealerCardPics();
            pScore.setText(player.playerHand.handValue());
            dScore.setText(dealer.dealerHand.handValue());
            dDown();
            bet.setDisable(true);
            betInput.setDisable(true);
            hit.setDisable(false);
            stand.setDisable(false);



        }catch (NumberFormatException e){
            message.setText("Please Enter a bet between 0 and your total balance");
        }
    }
    /**
     *Function to "hit" or get another card.
     */
    public void hitCard ()  {
        player.playerHand.getCard(gameShoe.dealCard());
        pScore.setText(player.playerHand.handValue());
        playerCardPics();
        doubleDown.setDisable(true);
        message.setText("It is your turn");
        if (Integer.parseInt(player.playerHand.handValue()) > 21) {
            endGame();
        }
    }
    /**
     *Function to "stand" players hand (aka end their turn).
     */
    public void standing (){
        hit.setDisable(true);
        stand.setDisable(true);
        doubleDown.setDisable(true);
        do {
            dealerTurn();
        }while (Integer.parseInt(dealer.dealerHand.handValue()) <= 17);
        if (Integer.parseInt(dealer.dealerHand.handValue()) >17){
            endGame();
        }

    }
    /**
     * Function to play the dealers turn with casino dealer logic.
     */
    public void dealerTurn () {
            hide.setOpacity(0);
            d2.setOpacity(100);
            if (Integer.parseInt(dealer.dealerHand.handValue()) <= 17 || dealer.dealerHand.ace > 0 && Integer.parseInt(dealer.dealerHand.handValue()) < Integer.parseInt(player.playerHand.handValue())) {
                dealer.dealerHand.getCard(gameShoe.dealCard());
                dScore.setText(dealer.dealerHand.handValue());
                dealerCardPics();
            } else {
                endGame();
            }

    }
    /**
     *function to check winner and end game. Also activates play again button.
     */
    public void endGame(){
        int a =Integer.parseInt(player.playerHand.handValue());
        int b =Integer.parseInt(dealer.dealerHand.handValue());
        restart.setDisable(false);
        restart.setOpacity(100);
        stand.setDisable(true);
        hit.setDisable(true);
        hide.setOpacity(0);
        d2.setOpacity(100);
        if (b > a && b < 22 || a > 21) {
            message.setText("Dealer Wins");
        }
        if (a == b) {
            message.setText("Push");
            player.setBalance(player.getBalance() + betAmount);
        }
        if (a > b && a <22|| b > 21 ){
            message.setText("You win! Congratulations!");
            player.setBalance(player.getBalance() + (2 * betAmount));
        }

    }
    /**
     * Function to check if the players hand is eligible to double down. enables button if it is.
     */

    public void dDown (){
        int a = Integer.parseInt(player.playerHand.handValue());
        if (a > 8 && a < 12 || player.playerHand.ace > 0 && a >=  19 && a < 21){
            doubleDown.setDisable(false);
            message.setText("You can double down! press the button if desired.");
        }
    }
    /**
     * function to enable players to double down.
     */
    public void doubling () {
        balance.setText(String.valueOf(player.getBalance() - betAmount));
        player.setBalance(player.getBalance() - betAmount);
        betAmount = betAmount * 2;
        message.setText("You have doubled down");
        doubleDown.setDisable(true);
        hitCard();
        standing();
    }
    /**
     *Function to restart game back to starting state. Also tops up player balance back to 5000 if they have fallen below 1000.
     */

     public void restartGame () {
         message.setText("Please Place your bet");
        if (player.getBalance() < 1000) {
            player.setBalance(5000);
            message.setText("your balance has been topped up. Place your bet.");
        }
         balance.setText(String.valueOf(player.getBalance()));
        restart.setDisable(true);
        restart.setOpacity(0);
        betInput.setText("0");
        betInput.setDisable(false);
        betAmount = 0;
        bet.setDisable(false);
        pScore.setText("0");
        dScore.setText("0");
        player.playerHand.reset();
        dealer.dealerHand.reset();
        player.playerHand.ace = 0;
        dealer.dealerHand.ace =0;
        if (gameShoe.cardsLeft() < 52){
            gameShoe.emptyShoe();
            gameShoe.refill(6);
        }

        clearImages();

    }
    /**
     * Function to reset all card images
     */
    public void clearImages() {
        p1.setImage(null);
        p2.setImage(null);
        p3.setImage(null);
        p4.setImage(null);
        p5.setImage(null);
        d1.setImage(null);
        d2.setImage(null);
        d3.setImage(null);
        d4.setImage(null);
        d5.setImage(null);
    }


}
