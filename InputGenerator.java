import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class InputGenerator {
    static String[][] TC_PLANS = {
        {"Small", "Sorted"},
        {"Small", "Random"},
        {"Small", "Reversed"},
        {"Medium", "Sorted"},
        {"Medium", "Random"},
        {"Medium", "Reversed"},
        {"Large", "Sorted"},
        {"Large", "Random"},
        {"Large", "Reversed"},
    };
    static Random rand = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < TC_PLANS.length; i++) {
            generateFileInput(i);
        }
    }

    public static void generateFileInput(int tcNumber) {
        int lotsOfData;
        if (TC_PLANS[tcNumber][0] == "Small") {
            lotsOfData = 500;
        } else if (TC_PLANS[tcNumber][0] == "Medium") {
            lotsOfData = 5000;
        } else {
            lotsOfData = 50000;
        }

        String type = TC_PLANS[tcNumber][1];

        try {
            File file = new File(String.format("test_cases/in/%sSize%s.in", TC_PLANS[tcNumber][0], type));

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            writer.write(String.format("%d\n", lotsOfData));

            if (type.equals("Sorted")) {
                writer.write(generateSortedInput(lotsOfData));
            } else if (type.equals("Random")) {
                writer.write(generateRandomInput(lotsOfData));
            } else {
                writer.write(generateReversedInput(lotsOfData));
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateSortedInput(int lotsOfData) {
        String input = "";
        for (int i = 0; i < lotsOfData; i++) {
            input += String.format("%d\n", i);
        }

        return input;
    }

    public static String generateRandomInput(int lotsOfData) {
        String input = "";
        int data;

        for (int i = 0; i < lotsOfData; i++) {
            data = rand.nextInt(lotsOfData + 1);
            input += String.format("%d\n", data);
        }

        return input;
    }

    public static String generateReversedInput(int lotsOfData) {
        String input = "";
        for (int i = lotsOfData - 1; i >= 0; i--) {
            input += String.format("%d\n", i);
        }

        return input;
    }
}
