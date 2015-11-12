
/**
 * This program is a scratch off game, where the player buy a single ticket. The ticket containing a hidden set of "seven numbers"
 * Also, the number is range from 1 -20 and it ask the player do he/she want to play again or not.
 * @author (Samuel Walton and Willam Thomas) 
 * Professor: Dr. Linda Sherrell
 * Data: 3/6/2013
 * Program Assignment 3
 */
import java.util.*;
public class ScratchOffGame
{
    // method generate the seven random numbers from one - seven.
    public static int []createdTickets ()
    {
        int [] ticket = new int[7];

        for(int i =0; i < ticket.length;i++)
        {
            ticket[i]= (int)(20*Math.random() +1);
            System.out.print(ticket[i] + " ");

        }
        return ticket;
    }

    //method calculate the total winning from the sheet provide and count how many numbers is match as well.
    public static double calculateWinnings(int[]numbers)
    {

        double totalWinning=0;
        int [] matching = new int [21];
        for(int s = 0; s <numbers.length; s++)

            matching[numbers[s]]++;

        for(int s = 1; s <matching.length; s++)
        {

            if( matching [s] ==1)

                matching[s]=0;
        }

        for(int i =1; i < 21;i++)
        {
            switch(matching [i])//
            {
                case 1:  totalWinning +=0;// if the ticket have no matching, the player wins nothing
                break;
                case 2: totalWinning += 0.1*(i);
                break;
                case 3: totalWinning += 0.5*(i);// if the ticket have three two matching numbers, the players wins 0.5x the dollar amount of matching numbers.  
                break;
                case 4: totalWinning += 1*(i);// if the ticket have four matching numbers, the players wins 1x the dollar amount of matching numbers.  
                break;
                case 5: totalWinning += 2*(i);// if the ticket have five matching numbers, the players wins 2x the dollar amount of matching numbers.  
                break;
                case 6: totalWinning += 5*(i);// if the ticket have six matching numbers, the players wins 5x the dollar amount of matching numbers.  
                break;
                case 7: totalWinning += 10*(i);// if the ticket have seven matching numbers, the players wins 10x the dollar amount of matching numbers.  
                break;
                default:totalWinning +=0;// default back to nothing.
                break;

            }

        }
        return totalWinning;
    }
    // the main method  allow the user to enter 1 for yes to play again or 2 for no. It also keep track of the total winning and total cost of ticket.
    public static void main ( String[] args)
    {

        Scanner input = new Scanner (System.in);
        int winningMoney[];
        double scratchOff, total=0;
        int answers,price=0;

        System.out.print("Each ticket cost $3 and consists of seven random scratch off numbers. Numbers range from 1- 20 ");// telling the user that the tickets cost 3$ per play.

        System.out.println();

        System.out.println(" Enter 1 for Yes or 2 for No. To play the scratch off game ");
        answers= input.nextInt();

        do
        {

            if(answers == 1)
            {

                winningMoney=createdTickets();
                scratchOff= calculateWinnings(winningMoney);

                System.out.println("You won: $ " + scratchOff + "  " + " Cost of the ticket is : $3");
                total+=scratchOff;
                price += (3);

            }
            if(answers == 1){
                System.out.println(" Enter 1 again to play or 2 for No. To play the scratch off game ");
                answers= input.nextInt();}
        }while(answers ==1);

        System.out.println("The total winning = $" + total + " " + "The total price for ticket = $ " + price + ".");// displaying the total winning and total coast of ticket.
    }
}

