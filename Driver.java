public class Driver {
    public static void main(String[] args) {

        String s = Reader.regexpToPostfix("0|1*0|1*");
        System.out.println(s);
    }
}
