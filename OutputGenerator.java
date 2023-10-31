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

    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String fileName;
        int idx;

        try {
            for (int i = 0; i < VALIDATION_PLANS.length * 2; i++) {
                if (i < VALIDATION_PLANS.length) {
                    fileName = "CountingSort";
                    idx = i;
                } else {
                    fileName = "BidirectionalConditionalInsertionSort";
                    idx = i - VALIDATION_PLANS.length;
                }

                String command = String.format("java %s.java < test_cases/%s.in > test_cases/%s.out", fileName, "in/" + VALIDATION_PLANS[idx], "out/" + VALIDATION_PLANS[idx] + fileName);
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
