package tutorial.finalReview;

public class Recursion {
    private static int sum(int[] array, int index) {
        //base case
        if (index == array.length - 1) {
            return array[index];
        } else {
            //recursive case
            return array[index] + sum(array, index + 1);
        }
    }

    public static int sum(int[] array) {
        return sum(array, 0);
    }

    private static int min(int[] array, int index) {
        //base case
        if (index == array.length - 1) {
            return array[index];
        } else {
            //recursive case
            int min = min(array, index + 1);
            if (array[index] < min) {
                return array[index];
            } else {
                return min;
            }
        }
    }

    public static int min(int[] array) {
        return min(array, 0);
    }

    public static String reverseString(String s) {
        return reverseString(s, 0);
    }

    private static String reverseString(String s, int index) {
        //base case
        if (index == s.length() - 1) {
            return Character.toString(s.charAt(index));
        }
        //recursive case
        char c = s.charAt(index);
        return reverseString(s, index + 1) + Character.toString(c);
    }

    public static int numberOfOccurrence(int number, int[] array) {
        return numberOfOccurrence(number, array, 0);
    }

    private static int numberOfOccurrence(int number, int[] array, int index) {
        //base case
        if (index == array.length) {
            return 0;
        }

        //recursive
        if (number == array[index]) {
            return 1 + numberOfOccurrence(number, array, index + 1);
        } else {
            return numberOfOccurrence(number, array, index + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(reverseString("Tommy"));
    }
}
