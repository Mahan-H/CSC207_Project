package osiris.use_case.grabtransactions;

public class GrabTransactionsInputData {

    //    private final String access_code;
    private final String username;


    public GrabTransactionsInputData(String username) {
        this.username = username;
    }


    String getUsername() {return username; }
}
