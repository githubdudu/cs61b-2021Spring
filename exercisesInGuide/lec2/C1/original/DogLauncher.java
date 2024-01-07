public class DogLauncher {
    public static void main(String[] args) {
//        Dog d = new Dog(20);
//        d.makeNoise();
        Dog d = new Dog(15);
        Dog d2 = new Dog(100);
        Dog.maxDog(d, d2);
    }
}