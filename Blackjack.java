//Daniel's Project 1 - BlackJack Simulation
//Examples used from ZyBooks Chapter 1, 2, 3(At least most of 3, as I wasn't finished entirely with it yet)
import java.util.Scanner;
import java.util.Random;

//Originally used import java.util.* which I saw certain devs use in examples, but I after reading up on it, I found that
//it would be cleaner if my code only listed what it needed

public class Blackjack {

    //This is the P1Random Method Class that was given for the project
    //No values were changed

    public static class P1Random
    {
        private long next;

        public P1Random()
        {
            next = 0;
        }

        private short nextShort()
        {
            return nextShort(Short.MAX_VALUE);
        }

        private short nextShort(short limit)
        {
            next = next * 1103515245 + 12345;
            return (short) Math.abs(((next / 65536) % limit));
        }

        private int nextInt()
        {
            return nextInt(Integer.MAX_VALUE);
        }

        public int nextInt(int limit)
        {
            return ((((int) nextShort()) << 16) | ((int) nextShort())) % limit;
        }

        private double nextDouble()
        {
            return (double) nextInt() / (double) Integer.MAX_VALUE;
        }
    }

    //P1Random Class Ending, Start of the Main

    public static void main(String args[]) {                                //Had an issue with the static now allowing the
                                                                    //P1Random class to operate!

        //Initilizing my variables for the experiment
        Scanner scanner = new Scanner(System.in);
        int rounds = 0; //Rounds
        double firstRound = 0; //Sets value for the first round
        int cards = 0; //Initiates Card Value
        int options = 1; //Initiates Options
        int pWins = 0; //Player wins set to 0
        double pWinsOne = 0; //Set up future totals
        int conWins = 0;
        int cons = 0; //Cons is name of dealer in this experiment
        int gamesToTie = 0; //Tied games counter
        double gamesToTieOne = 0;
        P1Random rng = new P1Random();

        do {                        //Sets up a new round by adding One to the total.

            rounds = rounds + 1;
            firstRound = firstRound + 1;

            int win = 0; //Win is set to zero

            System.out.println("START GAME #" + rounds + "\n");
            options = 1;

            //After this, hand is reset. Hopefully to afford confrontation to future rounds
            int theHands = 0;
            cons = 0;

            do {                                                    //Program knows what to do based on game situation

                if (options == 1) {

                    cards = rng.nextInt(13) + 1;//This sets the possibility of a random card
                    if (cards == 1) {
                        System.out.println("Your card is a ACE!");
                    } else if (cards == 11) {
                        cards = 10;
                        System.out.println("Your card is a JACK!");
                    } else if (cards == 12) {
                        cards = 10;
                        System.out.println("Your card is a QUEEN!");
                    } else if (cards == 13) {
                        cards = 10;
                        System.out.println("Your card is a KING!");
                    } else {
                        System.out.println("Your card is a " + cards + "!");
                    }

                    theHands = cards + theHands;

                    System.out.println("Your hand is: " + theHands); //Informs player what their hand currently is

                    //This is how the game knows to end the round if either the player or dealer hits a certain amount
                    if (theHands > 21) {
                        System.out.println("\nYou exceeded 21! You lose.\n");
                        conWins = conWins + 1;
                        break;
                    } else if (theHands == 21) {
                        System.out.println("\nBLACKJACK! You win!\n");
                        pWins = pWins + 1;
                        pWinsOne = pWinsOne + 1;
                        break;
                    }

                    //Option Menu for the game, players chose their next option.
                    System.out.println("1. Get another card");//Menu option to get another card
                    System.out.println("2. Hold hand");//Menu option to hold if the hand is good
                    System.out.println("3. Print statistics");//Print the stats that are listed just above
                    System.out.println("4. Exit");//Exit the application, terminates program from running further
                    System.out.print("Choose an option: ");//Option selection

                    options = scanner.nextInt();
                    System.out.print(""); //Input for the players next command in the game
                } else if (options == 2) {

                    cons = rng.nextInt(11) + 16; //Random limit for the NextInt
                    System.out.println("Dealer's hand: " + cons);
                    System.out.println("Your hand is: " + theHands + "\n");
                    //Sets up the process for the dealer/con getting their hand
                    if (theHands > cons && theHands <= 21) {
                        System.out.println("You win!");
                        win = 1;
                        pWins = pWins + 1;
                        pWinsOne = pWins + 1;
                    } else if (cons > theHands && cons <= 21) {
                        System.out.println("Dealer wins!");
                        win = 1;
                        conWins = conWins + 1;
                    } else if (cons > 21) {
                        System.out.println("You win!\n");
                        win = 1;
                        pWins = pWins + 1;
                        pWinsOne = pWinsOne + 1;
                    } else if (theHands == cons) {
                        System.out.println("It's a tie! No one wins!\n");
                        gamesToTie = gamesToTie + 1;
                        gamesToTieOne = gamesToTieOne + 1;
                        win = 1;
                    }
                    //Finish process of deals having hands during the games.
                } else if (options == 3) {
                    double percent = ((pWinsOne) / (firstRound - 1)) * 100;
                    int games = rounds - 1;
                    System.out.println("Number of Player wins: " + pWins); //Total Number of player wins this run
                    System.out.println("Number of Dealer wins: " + conWins); //Number of Dealer wins this round
                    System.out.println("Number of tie games: " + gamesToTie); //Number of times the game was tied
                    System.out.println("Total # of games played is: " + games); //Total amount of games played this round
                    System.out.println("Percentage of Player wins: " + percent + "%"); //The percetage that the player has won

                    System.out.println("1. Get another card"); //Menu option to get another card
                    System.out.println("2. Hold hand"); //Menu option to hold if the hand is good
                    System.out.println("3. Print statistics"); //Print the stats that are listed just above
                    System.out.println("4. Exit"); //Exit the application, terminates program from running further
                    System.out.print("Choose an option: "); //Option selection

                    options = scanner.nextInt();
                    System.out.print(""); //Player sees their chosen number.

                } else if (options == 4) {
                    break;
                } else {
                    System.out.println("Invalid input!Please enter an integer value between 1 and 4."); //Bad number
                    System.out.println("1. Get another card");
                    System.out.println("2. Hold hand");//Menu option to hold if the hand is good
                    System.out.println("3. Print statistics");//Print the stats that are listed just above
                    System.out.println("4. Exit");//Exit the application, terminates program from running further

                    System.out.print("Choose an option: ");
                    options = scanner.nextInt();
                    System.out.print("");

                    while (options > 4 || options < 0) {    //Option if player selects number that is higher than set
                        System.out.println("Invalid input!Please enter an integer value between 1 and 4.");
                        System.out.println("1. Get another card");
                        System.out.println("2. Hold hand");//Menu option to hold if the hand is good
                        System.out.println("3. Print statistics");//Print the stats that are listed just above
                        System.out.println("4. Exit");//Exit the application, terminates program from running further

                        System.out.print("Choose an option: "); //Initiate the final scanner in sequence
                        options = scanner.nextInt();
                        System.out.print("");
                    }
                }
            }
            while (win ==0);
        }
        while (options < 4);
    }
}

//Program End
//Still not used to methods so my work today is elongated. I hope in the future as I learn more that it will shorten :)
//Due 9/18/2021
//Now on GitHub!









