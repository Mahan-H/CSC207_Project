package osiris.use_case.viewexpenses;

import jakarta.transaction.Transaction;
import osiris.entity.User;
import osiris.entity.UserFactory;
import osiris.data_access.DBUserDataAccessObject;
import javax.swing.text.View;
import osiris.data_access.PlaidDataAccessObject;

import java.util.ArrayList;
import java.util.List;


public class ViewExpensesInteractor implements ViewExpensesInputBoundary{
    private final ViewExpensesUserDataAccessInterface userDataAccessObject;
    private final ViewExpensesOutputBoundary userPresenter;
    private final UserFactory userFactory;


    public ViewExpensesInteractor(ViewExpensesUserDataAccessInterface userDataAccessObject, ViewExpensesOutputBoundary userPresenter, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }


    @Override
    public void execute(ViewExpensesInputData viewExpensesInputData) {
              User user = userDataAccessObject.get(viewExpensesInputData.getUsername());
              String token = user.getAccessCode();
              String itemID = user.getItemID();






    }

    @Override
    public void switchToHome() {

    }
}



