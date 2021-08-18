/**
 * this class models a single deck of cards.
 *
 * @author Emmett Law Aug 2021
 */
package BlackJack;


import java.util.ArrayList;

public class Deck {

    private ArrayList<Cards> deck = new ArrayList<>();

    /**
     * default constructor to generate a deck
     */
    public Deck() {
        fill();
    }

    /**
     * function to fill a deck
     */
    public final void fill() {
        int i = 0;
        for (Cards.Suit suit : Cards.Suit.values()) {
            for (Cards.Rank rank : Cards.Rank.values()) {
                deck.add(new Cards(rank, suit));

            }
        }
    }

    /**
     * function to remove a card from the deck and return that card value to be added to the shoe
     * @return
     */
    public Cards addToShoe() {
        Cards card =  deck.get(deckSize() -1);
        deck.remove(card);
        return card;
    }

    /**
     * function to check the size of the deck
     * @return
     */
    public int deckSize (){
        return deck.size();
    }
}
