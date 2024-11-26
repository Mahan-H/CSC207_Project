package osiris.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a user's Plaid account details.
 */
@Entity
@Table(name = "user_plaid")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_client_id", unique = true, nullable = false)
    private String userClientId;

    @Column(name="access_token", nullable = false, length = 500)
    private String accessToken;

    @Column(name="item_id", nullable = false, length = 100)
    private String itemId;

    @Column(name="last_sync", nullable = false)
    private LocalDateTime lastSync;

    public BankAccount() {}

    public BankAccount(String userClientId, String accessToken, String itemId) {
        this.userClientId = userClientId;
        this.accessToken = accessToken;
        this.itemId = itemId;
        this.lastSync = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getUserClientId() {
        return userClientId;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public String getItemId() {
        return itemId;
    }


    public LocalDateTime getLastSync() {
        return lastSync;
    }

}
