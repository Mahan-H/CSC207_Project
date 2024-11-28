package osiris.use_case.grabtransactions;

import com.plaid.client.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public class GrabTransactionOutputData {
    private List<Transaction> transactions;
    private double totalSpending;
    private double totalIncome;
    private String statusMessage;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructor
    public GrabTransactionOutputData(List<Transaction> transactions, double totalSpending, double totalIncome, String statusMessage, LocalDate startDate, LocalDate endDate) {
        this.transactions = transactions;
        this.totalSpending = totalSpending;
        this.totalIncome = totalIncome;
        this.statusMessage = statusMessage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and setters
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(double totalSpending) {
        this.totalSpending = totalSpending;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
