package osiris.interface_adapter.grabtransaction;

import com.plaid.client.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import osiris.use_case.grabtransactions.GrabTransactionOutputData;
import osiris.use_case.grabtransactions.GrabTransactionsInputBoundary;
import osiris.use_case.grabtransactions.GrabTransactionsInputData;
import osiris.utility.exceptions.PlaidUseCaseException;

import java.io.IOException;
import java.util.List;



public class GrabTransactionController {
    private final GrabTransactionsInputBoundary grabTransactions;

    public GrabTransactionController(GrabTransactionsInputBoundary grabTransactions) {
        this.grabTransactions = grabTransactions;
    }

    /**
     * Endpoint to create a Plaid Link Token.
     *
     * @param username The request body containing necessary parameters.
     * @return CreateLinkTokenOutputData containing the link token.
     */
    public GrabTransactionOutputData createTransactions(String username) throws PlaidUseCaseException, IOException {
        GrabTransactionsInputData inputData = new GrabTransactionsInputData(username);
        return grabTransactions.fetchTransactions(inputData);
    }

}
