import java.util.Scanner;

public class StupidAssLoveMeter {

    public static void main(String[] args) {
        // Input string from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Name:");
        String name1 = scanner.nextLine();
        
        System.out.println("Enter Name:");
        String name2 = scanner.nextLine();
        
        // Process for both "NAME1+NAME2" and "NAME2+NAME1"
        String combination1 = name1.toUpperCase() + name2.toUpperCase();
        String combination2 = name2.toUpperCase() + name1.toUpperCase();

        //Get MAX LOVE
        int FinalLove = calculateLoveMeter(combination1) > calculateLoveMeter(combination2)? calculateLoveMeter(combination1) : calculateLoveMeter(combination2);

        System.out.println("Love Meter: " + FinalLove);
        scanner.close();
    }

    // Method to calculate love meter based on the input string
    public static int calculateLoveMeter(String input) {
        // Convert the string to a character array
        char[] characters = input.toCharArray();
        boolean[] counted = new boolean[characters.length]; // To track counted characters
        int[] frequencies = new int[characters.length];     // Array to store frequenciesz
        int uniqueIndex = 0;                                // Index for frequencies

        // Loop through each character to count frequencies
        for (int i = 0; i < characters.length; i++) {
            // If the character has already been counted, skip it
            if (counted[i]) {
                continue;
            }

            char currentChar = characters[i];
            int count = 1;

            // Check for repeated occurrences of the current character
            for (int j = i + 1; j < characters.length; j++) {
                if (characters[j] == currentChar) {
                    count++;
                    counted[j] = true; // Mark this character as counted
                }
            }

            // Store the frequency
            frequencies[uniqueIndex++] = count;
        }

        // Trim the frequency array to the actual number of unique characters
        int[] trimmedFrequencies = new int[uniqueIndex];
        System.arraycopy(frequencies, 0, trimmedFrequencies, 0, uniqueIndex);

        // Now, process the frequency array by summing outermost elements until only two are left
        while (trimmedFrequencies.length > 2) {
            trimmedFrequencies = sumOutermostDigits(trimmedFrequencies);
        }

        // Return the final percentage (sum of the last two numbers)
        return trimmedFrequencies[0] * 10 + trimmedFrequencies[1];
    }

    // Function to sum the outermost elements of the array
    public static int[] sumOutermostDigits(int[] arr) {
        int n = arr.length;
        int newLength = (n + 1) / 2; // Size of the new array (half the old array size, rounded up)
        int[] newArr = new int[newLength];

        for (int i = 0; i < n / 2; i++) {
            // Add the first and last elements
            newArr[i] = arr[i] + arr[n - 1 - i];
        }

        // If the array has an odd number of elements, copy the middle one
        if (n % 2 == 1) {
            newArr[newLength - 1] = arr[n / 2];
        }

        return newArr;
    }
}
