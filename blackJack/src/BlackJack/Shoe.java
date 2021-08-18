/**
 * this class models a shoe of cards. A shoe is a box used to hold multiple decks of cards.
 *
 * @author Emmett Law Aug 2021
 */
package BlackJack;


import java.util.ArrayList;
import java.util.Collections;

public class Shoe {
    private ArrayList<Cards> theShoe = new ArrayList<>();


    /**
     * default constructor for shoe. Generates a shoe containing the number of decks passed to it and then shuffles the shoe.
      * @param decks
     */
    public Shoe(int decks) {
        for (int x = 0; x < decks; x++) {
            addDeck(new Deck());
            shuffle();

        }

    }

    /**
     * function to refill the shoe when it is running low on cards
     * @param decks
     */
    public void refill(int decks) {
        for (int x = 0; x < decks; x++) {
            addDeck(new Deck());
            shuffle();

        }

    }

    /**
     * function to add a single deck to the shoe. Used in default constructor.
     * @param deck
     */
    public void addDeck(Deck deck){
        for (int i = 0; i< deck.deckSize(); i++){
            theShoe.add(deck.addToShoe());
        }
    }

    /**
     * function to shuffle the shoe
     */
    public void shuffle() {
        Collections.shuffle(theShoe);
    }

    /**
     * removes a card from the shoe and returns the value of that card to be added to a hand
     * @return
     */
    public Cards dealCard(){
        Cards card = theShoe.get(theShoe.size() - 1);
        theShoe.remove(card);
        return card;
    }

    /**
     * function to check the amount of cards left in the shoe, to know when to refill
     * @return
     */
    public int cardsLeft(){
        return theShoe.size();
    }

    /**
     * function to empty the shoe so it can be refilledSho
     */
    public void emptyShoe() {
        theShoe.clear();
    }







}
