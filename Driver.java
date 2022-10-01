public class Driver {
    public static void main(String[] args) {

        Autom thompson = Thompson.thompson("0101*++");
        Writer.write(thompson, "thompson.txt");

    }
}
