/**
 * this class models a player hand and provides functions to interact with hands.
 *
 * @author Emmett Law Aug 2021
 */
package BlackJack;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import BlackJack.Cards.Rank;

import java.util.ArrayList;


public class Hand {
    private ArrayList<Cards> hand = new ArrayList<>();
    private int value = 0;
    public int ace =0;


    /**
     * constructor for hand
     * @param cards
     */
    public Hand(ArrayList<Cards> cards) {
        this.hand = cards;
    }

    /**
     * default constructor for hand
     */
    public Hand() {

    }

    /**
     * function to add card to hand
     * @param card
     */
    public void getCard(Cards card) {
        hand.add(card);
    }


    /**
     * Function to get the total value of the hand and change the value of an ace if necessary
     * @return
     */
    public String handValue () {
        value =0;
        for(int i = 0; i< hand.size(); i++) {
            value += hand.get(i).getValue();
            if (hand.get(i).getValue() == 11) {
                ace++;
            }

        }
        if (value >21 && ace > 0){
            value -= 10;
            ace--;
        }
        return String.valueOf(value);
    }

    /**
     * function to reset a hand at the end of a game
     */
    public void reset(){
        hand.clear();
        value = 0;
    }

    /**
     * function to get the size of the hand
     * @return
     */
    public int size(){
        return hand.size();
    }

    /**
     * function to generate string that will be the filename for the correct picture of a card
     * @param i
     * @return
     */
    public String assignPic(int i) {
        Cards a =hand.get(i);
        String b = a.rank.toString() + a.suit.toString().charAt(0);
        return "Images/" + b + ".png";
    }

    /**
     * function to check if a hand is a natural (which is blackjack, or 21 in the first 2 cards)
     * @return
     */
    public boolean checkNatural () {
        if (Integer.parseInt(handValue()) == 21) {
            return true;
        }
        return false;
    }
}
