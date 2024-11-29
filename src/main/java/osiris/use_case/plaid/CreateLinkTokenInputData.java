package osiris.use_case.plaid;

/**
 * Input data for creating a Plaid Link Token.
 */
public class CreateLinkTokenInputData {
    private final String clientName;
    private final String[] countryCodes;
    private final String language;
    private final String userClientId;
    private final String[] products;

    public CreateLinkTokenInputData(String clientName, String[] countryCodes, String language,
                                    String userClientId, String[] products) {
        this.clientName = clientName;
        this.countryCodes = countryCodes;
        this.language = language;
        this.userClientId = userClientId;
        this.products = products;
    }

    public String getClientName() {
        return clientName;
    }

    public String[] getCountryCodes() {
        return countryCodes;
    }

    public String getLanguage() {
        return language;
    }

    public String getUserClientId() {
        return userClientId;
    }

    public String[] getProducts() {
        return products;
    }
}
