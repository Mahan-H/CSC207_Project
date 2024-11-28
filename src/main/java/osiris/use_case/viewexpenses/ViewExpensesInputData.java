package osiris.use_case.viewexpenses;

public class ViewExpensesInputData {
//    private final String access_code;
    private final String username;


    public ViewExpensesInputData(String access_code, String username) {
//        this.access_code = access_code;
        this.username = username;
    }

//    String getAccessCode() {
//        return access_code;
//    }

    String getUsername() {return username; }

}
