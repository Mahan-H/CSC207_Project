package osiris.use_case.viewexpenses;


import osiris.entity.User;

public interface ViewExpensesUserDataAccessInterface {

    /**
     * get method for username.
     * @param username the username to look for
     * @return the code
     */

    User get(String username);


}
