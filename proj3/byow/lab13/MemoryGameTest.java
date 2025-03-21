package byow.lab13;

public class MemoryGameTest {
    private MemoryGame mg;
    MemoryGameTest() {
        mg = new MemoryGame(40, 40, 123);
    }
    /**
     * Method used for testing the game.
     */
    public static void main(String[] args) {
        MemoryGameTest mgk = new MemoryGameTest();
//        mgk.testGenerateRandomString();
        mgk.testDrawFrame();
    }

    private void testGenerateRandomString() {
        // Test the generateRandomString() method
        for (int i = 0; i < 10; i++) {
            System.out.println(mg.generateRandomString(1));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(mg.generateRandomString(5));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(mg.generateRandomString(10));
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(mg.generateRandomString(50));
        }
    }
    private void testDrawFrame() {
        // Test the drawFrame() method
        mg.drawFrame("Hello, world!");
    }
}