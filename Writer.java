import java.io.FileWriter;

public class Writer {
    static void write(Autom autom, String type)
    {
        try {
            FileWriter write = new FileWriter(type);
            write.write(autom.toString());
            write.close();
            System.out.println("Archivo " + type + " creado.");
        } catch (Exception e) {
            System.out.println("Error al crear el archivo.");
        }
    }
}
