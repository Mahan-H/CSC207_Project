package osiris.use_case.verify;

public interface EmailService {

    void sendVerificationEmail(String sendingAddress, String subject, String body);
}
