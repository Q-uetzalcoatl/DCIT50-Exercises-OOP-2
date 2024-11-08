// Import necessary classes
import java.util.HashSet; // HashSet is used to store unique numbers and prevent duplicates
import java.util.Random;  // Random is used to generate random winning numbers for the lottery
import java.util.Scanner; // Scanner is used to take input from the user

class LotteryGame {
    private int[] userNumbers = new int[6];  // Store user's chosen numbers
    private int[] winningNumbers = new int[6];  // Store randomly generated winning numbers

    // Method to get input from user, ensuring they enter exactly six unique numbers between 1 and 45
    public void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        
        // Loop until user enters valid input
        while (true) {
            try {
                System.out.print("Enter six numbers (1-45) separated by spaces: ");
                
                // Read the entire line as a single input and trim any extra spaces
                String inputLine = scanner.nextLine().trim();
                
                // Split the input by spaces to get each number
                String[] input = inputLine.split("\\s+");  // Example: "5 12 20 33 41 6" -> ["5", "12", "20", "33", "41", "6"]
                
                // Check if user has entered exactly six numbers
                if (input.length != 6) {
                    System.out.println("Please enter exactly six numbers.");
                    continue;  // Ask for input again if not six numbers
                }
                
                // Create a HashSet to check for unique numbers
                Set<Integer> numbersSet = new HashSet<>();
                
                // Convert each input string to integer and check if it meets the criteria
                for (int i = 0; i < 6; i++) {
                    int number = Integer.parseInt(input[i]);  // Convert string to integer
                    
                    // Check if number is between 1 and 45, and if it is unique
                    if (number < 1 || number > 45 || !numbersSet.add(number)) {
                        throw new NumberFormatException();  // Trigger error if number is invalid or duplicate
                    }
                    userNumbers[i] = number;  // Store valid number in userNumbers array
                }
                break;  // Break out of the loop if all input is valid
                
            } catch (NumberFormatException e) {
                // This message appears if input is invalid or contains duplicates
                System.out.println("Invalid input. Enter six unique numbers between 1 and 45.");
            }
        }
    }

    // Method to generate six unique random winning numbers
    public void generateWinningNumbers() {
        Random random = new Random();
        
        // HashSet to store and ensure each winning number is unique
        Set<Integer> numbersSet = new HashSet<>();  // Prevents duplicate numbers
        
        System.out.println("Generating Results...");
        
        // Loop to generate 6 unique random numbers for winning numbers
        for (int i = 0; i < 6; i++) {
            int number;
            do {
                number = random.nextInt(45) + 1;  // Random number between 1 and 45
            } while (!numbersSet.add(number));  // Retry if the number is already in the set
            
            winningNumbers[i] = number;  // Store unique random number in winningNumbers array
            
            // Display each number one by one with a delay to create a "lottery draw effect"
            System.out.print("Winning Lottery Numbers: ");
            for (int j = 0; j <= i; j++) {
                System.out.print(winningNumbers[j] + " ");  // Print numbers as they are drawn
            }
            System.out.print("\r");  // Rewrite on the same line
            try {
                Thread.sleep(500);  // Delay for 0.5 second for the effect
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();  // Move to new line after the effect
    }

    // Method to check matches and calculate the reward based on match count
    public void calculateReward() {
        int matchCount = 0;  // Counter for the number of matches
        
        // Loop through each user number and check if it exists in the winning numbers
        for (int num : userNumbers) {
            for (int winNum : winningNumbers) {
                if (num == winNum) {  // Match found
                    matchCount++;
                    break;  // Stop inner loop after finding a match
                }
            }
        }

        // Determine reward based on the number of matches
        int reward = 0;
        switch (matchCount) {
            case 3: reward = 1000; break;   // 3 matches = PHP 1000
            case 4: reward = 2000; break;   // 4 matches = PHP 2000
            case 5: reward = 4000; break;   // 5 matches = PHP 4000
            case 6: reward = 8000; break;   // 6 matches = PHP 8000
            default: reward = 0;            // Less than 3 matches = no reward
        }

        // Display user's chosen numbers, winning numbers, and reward
        System.out.print("Your Numbers: ");
        for (int num : userNumbers) System.out.print(num + " ");
        System.out.println("\nWinning Lottery Numbers: ");
        for (int num : winningNumbers) System.out.print(num + " ");
        
        System.out.println("\nNumber of Matches: " + matchCount);
        System.out.println("Your reward is: PHP " + reward);
    }
}

public class Main {
    public static void main(String[] args) {
        // Create an instance of the LotteryGame class
        LotteryGame game = new LotteryGame();
        
        // Call methods to play the lottery game
        game.getUserInput();           // Step 1: Get user's chosen numbers
        game.generateWinningNumbers();  // Step 2: Generate and display winning numbers
        game.calculateReward();         // Step 3: Calculate and display reward based on matches
    }
}
