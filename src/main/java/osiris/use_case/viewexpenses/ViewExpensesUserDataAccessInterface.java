package osiris.use_case.viewexpenses;


public interface ViewExpensesUserDataAccessInterface {

    /**
     * get accesscode for username.
     * @param email the username to look for
     * @return the code
     */
    String getAccessCode(String email);

    String getItemID(String email);

}
