public class Driver {
    public static void main(String[] args) {

        String postfix = Reader.regexpToPostfix("b(ab(ab)*+(abab+b)*a)");
        System.out.println(postfix);
        Autom thompson = Thompson.thompson(postfix);
        Writer.write(thompson, "thompson.txt");
        DFA dfa = Subsets.subsets(thompson);
        Writer.write(dfa, "subsets.txt");
        DFA minimal = Minimize.minimize(dfa);
        Writer.write(minimal, "minimal.txt");

    }
}
