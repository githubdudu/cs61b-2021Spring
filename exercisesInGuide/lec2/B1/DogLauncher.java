public class DogLauncher {
    public static void main(String[] args) {
        Dog poppa = new Dog();
        poppa.bark();
//        Dog.bark(); // Cause an error
        poppa.runFast();
        Dog.runFast();
    }
}