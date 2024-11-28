package osiris.use_case.viewexpenses;

public class ViewExpensesInputData {
//    private final String access_code;
    private final String email;


    public ViewExpensesInputData(String access_code, String email) {
//        this.access_code = access_code;
        this.email = email;
    }

//    String getAccessCode() {
//        return access_code;
//    }

    String getEmail() {return email; }
}
