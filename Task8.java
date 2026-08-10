public class Task8 {

    public static int sumOfSums(int... numbers) {
        int cumulativeSum = 0;

        for (int n : numbers) {
            int triangularSum = n * (n + 1) / 2;

            cumulativeSum += triangularSum;

            System.out.printf("Parameter %d: sum = %d, cumulative sum = %d%n",
                    n, triangularSum, cumulativeSum);
        }

        return cumulativeSum;
    }

    public static void main(String[] args) {
        int total = sumOfSums(4, 5, 10);

        System.out.println("Total sum: " + total);
    }
}
