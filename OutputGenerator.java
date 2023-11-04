public class OutputGenerator {
    static String[] VALIDATION_PLANS = {
        "SmallSizeSorted", 
        "SmallSizeRandom",
        "SmallSizeReversed",
        "MediumSizeSorted", 
        "MediumSizeRandom",
        "MediumSizeReversed",
        "LargeSizeSorted", 
        "LargeSizeRandom",
        "LargeSizeReversed",
    };
    static String FILE_NAME = "BidirectionalConditionalInsertionSort";

    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();

        try {
            for (int i = 0; i < VALIDATION_PLANS.length; i++) {
                String command = String.format("java %s.java < test_cases/%s.in > test_cases/%s.out", FILE_NAME, "in/" + VALIDATION_PLANS[i], "out/" + VALIDATION_PLANS[i] + FILE_NAME);
                processBuilder.command("cmd.exe", "/c", command);
                Process process = processBuilder.start();

                int exitVal = process.waitFor();
                if (exitVal == 0) {
                    System.out.println("Success!");
                }else{
                    System.out.println("yah");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
