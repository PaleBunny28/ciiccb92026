public class MultiplicationTable {
    public static void main(String[] args) {
        
        System.out.println("===== 10 x 10 Multiplication Table =====\n");

        for (int row = 1; row <= 10; row++) {
            
            for (int col = 1; col <= 10; col++) {
                
                int product = row * col;
                
                System.out.printf("%4d", product);
            }
            
            System.out.println();
        }
    }
}
