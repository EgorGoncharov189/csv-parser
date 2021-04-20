import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Movements {
    private final List<PayData> payData = new ArrayList<>();
    public Movements (String pathMovementsCsv) {
        try {
            File csv = new File(pathMovementsCsv);
            List<String> fileLines = Files.readAllLines(Path.of(csv.getAbsolutePath()));
            fileLines.remove(0);
            fileLines.removeIf(line -> line.split(",").length != 8);
            String[] data;
            for (String line : fileLines) {
                data = line.split(",");
                PayData pd = new PayData(data[0], data[1], data[2], data[3], data[4], data[5],
                        Double.parseDouble(data[6]), Double.parseDouble(data[7]));
                payData.add(pd);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public double getExpenseSum () {
         double expense = 0;
         for (PayData pd : payData) {
             expense += pd.getExpense();
         }
         return expense;
    }

    public double getIncomeSum () {
        double income = 0;
        for (PayData pd : payData) {
            income += pd.getIncome();
        }
        return income;
    }

    public void printAmountsOfExpensesByOrganization () {
        List<String> outputData = new ArrayList<>();
        List<PayData> localPayData = new ArrayList<>(payData);
        filterExpense(localPayData);
        compareExpense(localPayData);
        for (int i = 0; i < localPayData.size(); i++) {
            var organization = getOrgName(localPayData.get(i));
            outputData.add(i + 1 + ". " + organization + " ---> " + localPayData.get(i).getExpense() + " руб.");
        }
        outputData.forEach(System.out::println);
    }

    public void printAmountsOfIncomesByOrganization () {
        List<String> outputData = new ArrayList<>();
        List<PayData> localPayData = new ArrayList<>(payData);
        filterIncome(localPayData);
        compareIncome(localPayData);
        for (int i = 0; i < localPayData.size(); i++) {
            var organization = getOrgName(localPayData.get(i));
            outputData.add(i + 1 + ". " + organization + " ---> " + localPayData.get(i).getIncome() + " руб.");
        }
        outputData.forEach(System.out::println);
    }

    private void compareExpense (List<PayData> pd) {
        Comparator<PayData> comparator = (elem1, elem2) -> {
            if (elem1.getExpense() > elem2.getExpense()) {
                return -1;
            } else if (elem1.getExpense() < elem2.getExpense()) {
                return 1;
            }
            return 0;
        };
        pd.sort(comparator);
    }

    private void compareIncome (List<PayData> pd) {
        Comparator<PayData> comparator = (elem1, elem2) -> {
            if (elem1.getIncome() > elem2.getIncome()) {
                return -1;
            } else if (elem1.getIncome() < elem2.getIncome()) {
                return 1;
            }
            return 0;
        };
        pd.sort(comparator);
    }

    private String getOrgName (PayData pd) {
        String[] splitContainer = pd.getDescriptionOfOperation().split("\\\\");
        return splitContainer[splitContainer.length - 1].split("\\s")[0];
    }

    private void filterExpense (List<PayData> pd) {
        pd.removeIf(expense -> expense.getExpense() <= 0);
    }

    private void filterIncome (List<PayData> pd) {
        pd.removeIf(expense -> expense.getIncome() <= 0);
    }
}
