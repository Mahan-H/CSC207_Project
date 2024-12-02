package osiris.app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();

        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addVerifyView()
                .addVerifyUseCase()
                .addWelcomeView()
                .addDashboardView()
                .addSignupUseCase()
                .addWelcomeUseCase()
                .addLoginUseCase()
                .addDashboardUseCase()
//                .addChangePasswordUseCase()
//                .addLogoutUseCase()
                .addViewExpensesView()
                .addViewExpensesUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
