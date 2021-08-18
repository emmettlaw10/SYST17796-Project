/**
 * this class models the human player and tracks their balance.
 *
 * @author Emmett Law Aug 2021
 */
package BlackJack;

public class Player {
     Hand playerHand = new Hand();
     private int balance =5000;

     /**
      * getter for player balance
      * @return
      */
     public int getBalance () {
          return balance;
     }

     /**
      * setter for player balance
      * @param balance
      */
     public void setBalance(int balance) {
          this.balance = balance;
     }









}
