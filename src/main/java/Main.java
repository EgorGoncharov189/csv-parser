public class Main {
    private static final String PATH = "C:\\Skillbox Egor Goncharov\\java_basics\\09_FilesAndNetwork\\files\\movementList.csv";
    public static void main(String[] args) {
        parse();
    }

    private static void parse () {
        Movements movements = new Movements(PATH);
        System.out.println("Сумма расходов: " + movements.getExpenseSum() + "\nСумма доходов: " + movements.getIncomeSum() + "\n");
        System.out.println("Суммы расходов по организациям: ");
        movements.printAmountsOfExpensesByOrganization();
        System.out.println("\nСуммы доходов по организациям: ");
        movements.printAmountsOfIncomesByOrganization();
    }
}
