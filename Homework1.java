import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.*;

public class Homework1 {

    private static int[] readFile() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            int a = sc.nextInt();
            int b = sc.nextInt();
            return new int[]{a, b};
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    private static void writeFile(long number) {
        try {
            Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("output.txt"), StandardCharsets.US_ASCII));
            writer.write(new Integer(number).toString());
            writer.close();
        } catch (IOException ex) {

        }
    }

    public static void main(String[] args) {
        int[] vals = readFile();
        long mult = vals[0] * vals[1];
        writeFile(mult);
    }
}