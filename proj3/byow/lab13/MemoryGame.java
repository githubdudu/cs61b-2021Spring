package byow.lab13;

import byow.Core.RandomUtils;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.stream.Collectors;

public class MemoryGame {
    /** The width of the window of this game. */
    private int width;
    /** The height of the window of this game. */
    private int height;
    /** The current round the user is on. */
    private int round;
    /** The Random object used to randomly generate Strings. */
    private Random rand;
    /** Whether or not the game is over. */
    private boolean gameOver;
    /** Whether or not it is the player's turn. Used in the last section of the
     * spec, 'Helpful UI'. */
    private boolean playerTurn;
    /** The characters we generate random Strings from. */
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    /** Encouraging phrases. Used in the last section of the spec, 'Helpful UI'. */
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        long seed = Long.parseLong(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        // Initialize random number generator
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        // Generate random string of letters of length n
        String randomString = rand.ints(n, 0, CHARACTERS.length)
                .mapToObj(i -> String.valueOf(CHARACTERS[i]))
                .collect(Collectors.joining());
        return randomString;
    }

    public void drawFrame(String s, boolean playerTurn) {
        // Take the string and display it in the center of the screen
        StdDraw.clear(Color.BLACK); // Clear the canvas with same color above
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 30));
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(width/2.0, height/2.0, s);

        //TODO: If game is not over, display relevant game information at the top of the screen
        if (!gameOver) {
            StdDraw.setFont(new Font("Monaco", Font.BOLD, 20));
            StdDraw.textLeft(1, height - 1, "Round: " + round);
            StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[RandomUtils.uniform(rand, ENCOURAGEMENT.length)]);
            StdDraw.text(width / 2.0, height - 1, playerTurn ? "Type!" : "Watch!");
            StdDraw.line(0, height - 2, width, height - 2);
        }
        StdDraw.show();
    }

    public void drawFrame(String s) {
        drawFrame(s, false);
    }

    public void flashSequence(String letters) {
        // Display each character in letters, show for 1 second and pause for 0.5 second
        letters.chars().forEach(c -> {
            drawFrame(String.valueOf((char) c));
            StdDraw.pause(1000); // Show for 1 second
            drawFrame("");
            StdDraw.pause(500); // Pause for 500 milliseconds
        });
    }

    public String solicitNCharsInput(int n) {
        String userInput = "";
        // Read n letters of player input
        for (int i = 0; i < n; i++) {
            while (true) {
                if (StdDraw.hasNextKeyTyped()) {
                    char c = StdDraw.nextKeyTyped();
                    userInput += c;
                    drawFrame(userInput, true);
                    break;
                }
            }
        }
        StdDraw.pause(1000);
        return userInput;
    }

    public void startGame() {
        // Set any relevant variables before the game starts
        round = 1; // Start game at round 1
        gameOver = false;

        // Establish Engine loop
        while (!gameOver) {
            drawFrame("Round " + round);
            StdDraw.pause(1000);

            String randomString = generateRandomString(round);
            flashSequence(randomString);

            drawFrame("", true);
            String userInput = solicitNCharsInput(round);
            if (!userInput.equals(randomString)) {
                gameOver = true;
                drawFrame("Game Over! You made it to round: " + round);
            } else {
                StdDraw.pause(1000);
                round += 1;
            }
        }
    }
}
