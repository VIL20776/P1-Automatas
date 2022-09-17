public class Driver {
    public static void main(String[] args) {

        String s = Reader.regexpToPostfix("01*(0+1)*");
        System.out.println(s);
    }
}
