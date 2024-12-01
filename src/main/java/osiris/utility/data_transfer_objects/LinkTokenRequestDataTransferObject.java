package osiris.utility.data_transfer_objects;

/**
 * Data Transfer Object for Link Token Requests.
 */
public class LinkTokenRequestDataTransferObject {
    private String clientName;
    private String[] countryCodes;
    private String language;
    private String userClientId;
    private String[] products;

    // Getters and setters

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String[] getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(String[] countryCodes) {
        this.countryCodes = countryCodes;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUserClientId() {
        return userClientId;
    }

    public void setUserClientId(String userClientId) {
        this.userClientId = userClientId;
    }

    public String[] getProducts() {
        return products;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }
}
