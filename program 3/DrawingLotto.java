/*This program prompts the user to enter integer values corresponding to the numbers drawn in a lottery game and the range
 * of the drawn numbers. Then, it asks what sub-type of drawing game the user wishes to play(with or without a bonus number).
 * If there is a bonus number the user enters its range as well. The user then selects his/her numbers(no repeats) within the
 * given range. The program then generates, randomly, the winning numbers. The program then compares both arrays and states 
 * whether the user won or lost the lottery. Also, the program calls methods to calculate and show the likelihood of the 
 * user winning the lottery. Error-checking has been implemented for all user input.
 * Author: William Thomas and Samuel Walton
 * Professor: Dr. Linda Sherrell
 * Instructor: Arpitha Myla
 * Section: 102
 * 6 April 2013
 * Programming Assignment #3
 */
import java.util.Scanner;
public class DrawingLotto
{
    //method for  to calculate chance of winning in game without bonus number
    public static double jackpotChance(int k, int n)
    {
        double numerator=1, denominator=1, result, chance;

        for (int j = 1; j <= k; j++)
        {
            denominator *= j;
        }

        for (int i = n-k+1; i <= n; i++)
        {
            numerator *= i;
        }

        result = numerator/denominator;
        chance = 1/result;
        return chance;
    }

    //method for  to calculate chance of winning in game with bonus number
    public static double jackpotChance(int k, int n, int m)
    {
        double numerator=1, denominator=1, result, chance;

        for (int j = 1; j <= k; j++)
        {
            denominator *= j;
        }

        for (int i = n-k+1; i <= n; i++)
        {
            numerator *= i;
        }

        result = (numerator/denominator) * m;
        chance = 1/result;
        return chance;
    }

    //method for game without bonus number
    public static void typeOneResult(int yourNumbers[], int winningNumbers[])
    {
        //variable used to count the number of matches
        int matches = 0;
        //adds one to the number of matches if there is a match
        for (int i = 0; i < yourNumbers.length; i++)
        {
            for (int j = 0; j < yourNumbers.length; j++)
            {
                if (yourNumbers[i] == winningNumbers[j])
                {
                    matches ++;
                }
            }
        }
        //If all numbers match, then the user wins.
        if (matches == yourNumbers.length)
        {
            System.out.println("\nYOU WIN!!!");
        } 
        else 
        {
            System.out.println("\nYou lose :(");
        }
    }

    //method for game with bonus number
    public static void typeTwoResult(int yourNumbers[], int winningNumbers[], int bonusChoice, int bonusWinner)
    {
        //variable used to count the number of matches
        int matches = 0;
        //adds one to the number of matches if there is a match
        for (int i = 0; i < yourNumbers.length; i++)
        {
            for (int j = 0; j < yourNumbers.length; j++)
            {
                if (yourNumbers[i] == winningNumbers[j])
                {
                    matches ++;
                }
            }
        }
        //If all numbers and the bonus number match, then the user wins.
        if (matches == yourNumbers.length && bonusChoice == bonusWinner)
        {
            System.out.println("\nYOU WIN!!!");
        } 
        else 
        {
            System.out.println("\nYou lose :(");
        }
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        //These lines ask for amount of numbers chosen, total range of numbers to choose from, 
        //if there is a bonus number (what type of drawing game it is), and the range of numbers of the 
        //bonus number (w/ error-checking).

        System.out.print("How many numbers are being drawn? ");
        int k = input.nextInt();

        while (k < 1)
        {
            System.out.print("The number has to be larger than zero. Try again. ");
            k = input.nextInt();
        }

        System.out.print("How many total numbers are there to choose from? ");
        int n = input.nextInt();

        while (k > n)
        {
            System.out.print("The total range of numbers has to be larger than the amount of numbers chosen. Try again. ");
            n = input.nextInt();
        }

        System.out.print("Which sub-type of drawing game? (1 = without bonus number, 2 = with bonus number) ");
        int drawingType = input.nextInt();

        while (drawingType < 1 || drawingType > 2)
        {
            System.out.print("You get two choices, buddy. Try again. ");
            drawingType = input.nextInt();
        }

        int m = 1;
        if (drawingType == 2)
        {
            System.out.print("How many numbers can you choose from for the bonus number? ");
            m = input.nextInt();

            while (m < 1)
            {
                System.out.print("The number has to be larger than zero. Try again. ");
                m = input.nextInt();
            }
        }

        //displays the probability of winning
        if (drawingType == 1)
        {
            System.out.println("\nYou have a " + jackpotChance(k, n) + " chance of winning.");
        }
        else
        {
            System.out.println("\nYou have a " + jackpotChance(k, n, m) + " chance of winning.");
        }

        //Lets the user make his/her picks and error-checks to make sure that the same number is not entered twice and all numbers 
        //are within appropriate range.
        System.out.println("Choose your numbers.\n");
        int[] yourNumbers = new int[k];

        for (int i = 0; i < k; i++)
        {
            System.out.print("Number " + (i + 1) + ": ");
            yourNumbers[i] = input.nextInt();

            while (yourNumbers[i] < 1 || yourNumbers[i] > n)
            {
                System.out.print("Enter an appropriate number: ");
                yourNumbers[i] = input.nextInt();
            }
            //checks the entered number against the previous numbers in the array
            for (int j = 0; j < i; j++)
            {
                if (yourNumbers[i] == yourNumbers[j])
                {
                    System.out.print("Enter a new, appropriate value. Try again: ");
                    i--;
                }
            }
        }

        //Lets the user enter a bonus number and selects the winning bonus number.
        int bonusChoice = 0;
        int bonusWinner = 0;

        if (drawingType == 2)
        {
            System.out.print("What number do you choose for bonus number?");
            bonusChoice = input.nextInt();

            while (bonusChoice < 1 || bonusChoice > m)
            {
                System.out.print("Enter an appropriate number: ");
                bonusChoice = input.nextInt();
            }

            bonusWinner = (int)(m*Math.random() + 1);
        }

        //Selects the winning numbers.
        int[] winningNumbers = new int[k];

        for (int i = 0; i < k; i ++)
        {
            winningNumbers[i] = (int)(n*Math.random()+1);

            //checks to make sure that the same number is not selected more than once
            for (int j = 0; j < i; j++)
            {
                while (winningNumbers[i] == winningNumbers[j])
                {
                    winningNumbers[i] = (int)(n*Math.random()+1);
                }
            }
        }

        //sends the user picks and winning numbers to the appropriate method
        if (drawingType == 2)
        {
            typeTwoResult(yourNumbers, winningNumbers, bonusChoice, bonusWinner);
        }
        else
        {
            typeOneResult(yourNumbers, winningNumbers);
        }

        //displays the user's picks
        System.out.print("\nYou picked: ");
        for (int i = 0; i < k; i ++)
        {
            System.out.print(yourNumbers[i] + " ");
        }

        if (drawingType == 2)
        {
            System.out.println("Bonus Number: " + bonusChoice);
        }

        //displays the winning numbers
        System.out.print("\nThe winning numbers are: ");
        for (int i = 0; i < k; i ++)
        {
            System.out.print(winningNumbers[i] + " ");
        }

        if (drawingType == 2)
        {
            System.out.println("Bonus Number: " + bonusWinner);
        }
    }
}