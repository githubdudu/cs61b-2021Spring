public class testPeople {
    public static void main(String[] args) {
        Person n = new Person("Neil", 12);
        Person a = new Grandma("Ada", 60);
        Grandma v = new Grandma("Vidya", 80);

        n.greet(a);
        n.greet(v);
        v.greet(a);
        v.greet((Grandma) a);

        a.greet(n);
        a.greet(v);

        ((Grandma) a).greet(v);
//        ((Grandma) n).greet(v);
    }
}
