public class PayData {
    private final String accountType;
    private final String currency;
    private final String transactionReference;
    private final String descriptionOfOperation;
    private final String dateOfOperation;
    private final String accountNumber; //Делаю string потому-что в long не влезает!!
    private final double income;
    private final double expense;
    public PayData (String accountType, String accountNumber, String currency,
                    String dateOfOperation, String transactionReference, String descriptionOfOperation, double income, double expense) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.dateOfOperation = dateOfOperation;
        this.transactionReference = transactionReference;
        this.descriptionOfOperation = descriptionOfOperation;
        this.income = income;
        this.expense = expense;
    }

    public String getDescriptionOfOperation () {
        return descriptionOfOperation;
    }

    public double getIncome () {
        return income;
    }

    public double getExpense () {
        return expense;
    }

    @Override
    public String toString () {
        return "Account type: " + accountType + "\nAccount number: " + accountNumber + "\nCurrency: " + currency + "\nDate of operation: " +
                dateOfOperation + "\nTransaction reference: " + transactionReference + "\nDescription of operation: " +
                descriptionOfOperation.replaceAll("( +)"," ").trim() + "\nIncome: " + income + " " + currency +
                "\nExpense: " + expense + " " + currency + "\n\n==== --------- ====\n\n";
    }
}
