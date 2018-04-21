package implementations;

import interfaces.Card;

public class PlayingCard implements Card {

	 private Card.Rank rank;
	 private Card.Suit suit;

	    public PlayingCard(Card.Rank rank, Card.Suit suit) {
	        this.rank = rank;
	        this.suit = suit;
	    }
	    
	    public Card.Suit getSuit() {
	        return suit;
	    }

	    public Card.Rank getRank() {
	        return rank;
	    }
	    
	    public boolean equals(Object obj) {
	        if (obj instanceof Card) {
	            if (((Card)obj).getRank() == this.rank &&
	                ((Card)obj).getSuit() == this.suit) {
	                return true;
	            } else {
	                return false;
	            }
	        } else {
	            return false;
	        }
	    }
	    

	    public int hashCode() {
	        return ((suit.value()-1)*13)+rank.value();
	    }

	    public int compareTo(Card o) {
	        return this.hashCode() - o.hashCode();
	    }

	    public String toString() {
	        return this.rank.text() + " of " + this.suit.text();
	    }

	    public static void main(String... args) {
	        //new PlayingCard(Rank.ACE, Suit.DIAMONDS);
	        //new PlayingCard(Rank.KING, Suit.SPADES);
	    	 String searchMe = "Green Eggs and Ham";
	         String findMe = "Eggs";
	         int searchMeLength = searchMe.length();
	         int findMeLength = findMe.length();
	         boolean foundIt = false;
	         for (int i = 0; 
	                 i <= (searchMeLength - findMeLength);
	                 i++) {
	               if (searchMe.regionMatches(i, findMe, 0, findMeLength)) {
	                  foundIt = true;
	                  System.out.println(searchMe.substring(i, i + findMeLength));
	                  break;
	               }
	            }
	    }

}
