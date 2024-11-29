package osiris.use_case.grabtransactions;

import com.plaid.client.model.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class GrabTransactionOutputData {
    // Getters and setters
    private List<Transaction> transactions;


    // Constructor
    public GrabTransactionOutputData(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }



//        public double getTotalSpending() {
//        return totalSpending;
//    }
//
//    public void setTotalSpending(double totalSpending) {
//        this.totalSpending = totalSpending;
//    }
//
//    public double getTotalIncome() {
//        return totalIncome;
//    }
//
//    public void setTotalIncome(double totalIncome) {
//        this.totalIncome = totalIncome;
//    }
//
//    public String getStatusMessage() {
//        return statusMessage;
//    }
//
//    public void setStatusMessage(String statusMessage) {
//        this.statusMessage = statusMessage;
//    }
//
//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }
//
//    public LocalDate getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDate endDate) {
//        this.endDate = endDate;
//    }
}
