/**
 * this class models a Card
 *
 * @author Emmett Law Aug 2021
 */
package BlackJack;



public class Cards {

    public final Rank rank;
    public final Suit suit;

    /**
     * parameters of a card
     */
    enum Suit  {CLUBS, DIAMONDS, HEARTS, SPADES};
    enum Rank {ACE(11),TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10) ;

        /**
         * assigning the variable name value to the rank values
         */
        private int value;

        Rank(int value) {
            this.value = value;
         }

        /**
         * function to return the rank of a card
         * @return
         */
        @Override
        public String toString() {
             return name();
     }


    };


    /**
     * constructor for a card
     * @param rank
     * @param suit
     */
    public Cards(Rank rank, Suit suit){
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * function to get the rank value of a card
     * @return
     */
    public int getValue(){
        return rank.value;
    }

    /**
     * string to output formatted text of card name
     * @return
     */
    @Override
    public String toString(){
        return rank + " of " + suit;
    }

}
