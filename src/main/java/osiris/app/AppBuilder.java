package osiris.app;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import osiris.data_access.DBUserDataAccessObject;
import osiris.data_access.PlaidDataAccessObject;
import osiris.entity.CommonUserFactory;
import osiris.entity.UserFactory;
import osiris.interface_adapter.ViewManagerModel;
import osiris.interface_adapter.dashboard.DashboardController;
import osiris.interface_adapter.dashboard.DashboardPresenter;
import osiris.interface_adapter.dashboard.DashboardViewModel;
import osiris.interface_adapter.grabtransaction.GrabTransactionController;
import osiris.interface_adapter.login.LoginController;
import osiris.interface_adapter.login.LoginPresenter;
import osiris.interface_adapter.login.LoginViewModel;
import osiris.interface_adapter.signup.SignupController;
import osiris.interface_adapter.signup.SignupPresenter;
import osiris.interface_adapter.signup.SignupViewModel;
import osiris.interface_adapter.viewexpenses.ViewExpensesController;
import osiris.interface_adapter.viewexpenses.ViewExpensesPresenter;
import osiris.interface_adapter.viewexpenses.ViewExpensesViewModel;
import osiris.interface_adapter.welcome.WelcomeController;
import osiris.interface_adapter.welcome.WelcomePresenter;
import osiris.interface_adapter.welcome.WelcomeViewModel;
import osiris.interface_adapter.verify.VerifyController;
import osiris.interface_adapter.verify.VerifyPresenter;
import osiris.interface_adapter.verify.VerifyViewModel;
import osiris.use_case.dashboard.DashboardInputBoundary;
import osiris.use_case.dashboard.DashboardInteractor;
import osiris.use_case.dashboard.DashboardOutputBoundary;
import osiris.use_case.grabtransactions.GrabTransactionUserDataAccessInterface;
import osiris.use_case.grabtransactions.GrabTransactionsInputBoundary;
import osiris.use_case.grabtransactions.GrabTransactionsInteractor;
import osiris.use_case.login.LoginInputBoundary;
import osiris.use_case.login.LoginInteractor;
import osiris.use_case.login.LoginOutputBoundary;
import osiris.use_case.verify.VerifyInputBoundary;
import osiris.use_case.verify.VerifyInteractor;
import osiris.use_case.verify.VerifyOutputBoundary;
import osiris.use_case.signup.SignupInputBoundary;
import osiris.use_case.signup.SignupInteractor;
import osiris.use_case.signup.SignupOutputBoundary;
import osiris.use_case.viewexpenses.ViewExpensesInputBoundary;
import osiris.use_case.viewexpenses.ViewExpensesInteractor;
import osiris.use_case.viewexpenses.ViewExpensesOutputBoundary;
import osiris.use_case.welcome.WelcomeInputBoundary;
import osiris.use_case.welcome.WelcomeInteractor;
import osiris.use_case.welcome.WelcomeOutputBoundary;
import osiris.view.*;


/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory);
    private final GrabTransactionUserDataAccessInterface grabTransactionUserDataAccessInterface =
            new DBUserDataAccessObject(userFactory);
    private final PlaidDataAccessObject plaidDataAccessObject = new PlaidDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoginView loginView;
    private WelcomeView welcomeView;
    private WelcomeViewModel welcomeViewModel;
    private VerifyViewModel verifyViewModel;
    private VerifyView verifyView;
    private DashboardView dashboardView;
    private DashboardViewModel dashboardViewModel;
    private ViewExpenses viewExpenses;
    private ViewExpensesViewModel viewExpensesViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
        cardPanel.setPreferredSize(new Dimension(850, 550));
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the ViewExpenses View to the application.
     * @return this builder
     */
    public AppBuilder addViewExpensesView() {
        viewExpensesViewModel = new ViewExpensesViewModel();
        viewExpenses = new ViewExpenses(viewExpensesViewModel);
        cardPanel.add(viewExpenses, viewExpenses.getViewName());
        return this;
    }

    public AppBuilder addWelcomeView() {
        welcomeViewModel = new WelcomeViewModel();
        welcomeView = new WelcomeView(welcomeViewModel);
        cardPanel.add(welcomeView, welcomeView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addDashboardView() {
        dashboardViewModel = new DashboardViewModel();
        dashboardView = new DashboardView(dashboardViewModel);
        cardPanel.add(dashboardView, dashboardView.getViewName());
        return this;
    }

    /**
     * Adds the welcome Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDashboardUseCase() {
        final DashboardOutputBoundary dashboardOutputBoundary = new DashboardPresenter(viewManagerModel,
                dashboardViewModel, viewExpensesViewModel);
        final DashboardInputBoundary userDashboardInteractor = new DashboardInteractor(dashboardOutputBoundary,
                userFactory);

        final DashboardController controller = new DashboardController(userDashboardInteractor);
        dashboardView.setDashboardController(controller);
        return this;
    }

    /**
     * Adds the welcome Use Case to the application.
     * @return this builder
     */
    public AppBuilder addWelcomeUseCase() {
        final WelcomeOutputBoundary welcomeOutputBoundary = new WelcomePresenter(viewManagerModel,
                welcomeViewModel, loginViewModel, signupViewModel);
        final WelcomeInputBoundary userWelcomeInteractor = new WelcomeInteractor(welcomeOutputBoundary);

        final WelcomeController controller = new WelcomeController(userWelcomeInteractor);
        welcomeView.setWelcomeController(controller);
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Verify View to the application.
     * @return this builder
     */

    public AppBuilder addVerifyView() {
        // Initialize ViewModel
        verifyViewModel = new VerifyViewModel();

        // Initialize Presenter (OutputBoundary)
        final VerifyOutputBoundary verifyOutputBoundary =
                new VerifyPresenter(verifyViewModel, signupViewModel, viewManagerModel);

        // Initialize Interactor (InputBoundary)
        final VerifyInputBoundary verifyInteractor =
                new VerifyInteractor(verifyOutputBoundary);

        // Initialize Controller
        final VerifyController verifyController =
                new VerifyController(verifyInteractor);

        // Initialize View and inject Controller
        verifyView = new VerifyView(verifyViewModel);
        verifyView.setVerifyController(verifyController);

        // Add View to the card panel
        cardPanel.add(verifyView, verifyView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, verifyViewModel, welcomeViewModel, dashboardViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the ViewExpenses View to the application.
     * @return this builder
     */
    public AppBuilder addViewExpensesUseCase() {
        final ViewExpensesOutputBoundary viewExpensesOutputBoundary = new ViewExpensesPresenter(viewExpensesViewModel,
                viewManagerModel);

        final ViewExpensesInputBoundary userViewExpensesInteractor = new ViewExpensesInteractor(
                viewExpensesOutputBoundary);

        final ViewExpensesController controller = new ViewExpensesController(userViewExpensesInteractor);
        viewExpenses.setViewExpensesController(controller);

        final GrabTransactionsInputBoundary grabTransactionsInteractor = new GrabTransactionsInteractor(
                grabTransactionUserDataAccessInterface, plaidDataAccessObject);
        final GrabTransactionController grabTransactionController = new GrabTransactionController(grabTransactionsInteractor);
        viewExpenses.setGrabTransactionController(grabTransactionController);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        // Create the LoginOutputBoundary (Presenter)
        final LoginOutputBoundary loginOutputBoundary =
                new LoginPresenter(viewManagerModel, loginViewModel, welcomeViewModel, verifyViewModel, dashboardViewModel);

        // Create the LoginInputBoundary (Interactor)
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, userFactory, plaidDataAccessObject, userDataAccessObject);

        // Create the LoginController
        final LoginController loginController =
                new LoginController(loginInteractor);

        // Add CAPTCHA integration in Login View
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
//    public AppBuilder addChangePasswordUseCase() {
//        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
//                new ChangePasswordPresenter(loggedInViewModel);
//
//        final ChangePasswordInputBoundary changePasswordInteractor =
//                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);
//
//        final ChangePasswordController changePasswordController =
//                new ChangePasswordController(changePasswordInteractor);
//        loggedInView.setChangePasswordController(changePasswordController);
//        return this;
//    }

    public AppBuilder addVerifyUseCase() {
        final VerifyViewModel verifyViewModel = new VerifyViewModel();

        final VerifyOutputBoundary verifyOutputBoundary =
                new VerifyPresenter(verifyViewModel, signupViewModel, viewManagerModel);

        final VerifyInputBoundary verifyInteractor =
                new VerifyInteractor(verifyOutputBoundary);

        final VerifyController verifyController =
                new VerifyController(verifyInteractor);

        verifyView.setVerifyController(verifyController); // Attach controller to view
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
//    public AppBuilder addLogoutUseCase() {
//        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
//                loggedInViewModel, loginViewModel);
//
//        final LogoutInputBoundary logoutInteractor =
//                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);
//
//        final LogoutController logoutController = new LogoutController(logoutInteractor);
//        loggedInView.setLogoutController(logoutController);
//        return this;
//    }


    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Osiris");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(850, 550);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension frameSize = application.getSize();
        final int x = (screenSize.width - frameSize.width) / 2;
        final int y = (screenSize.height - frameSize.height) / 2;
        application.setLocation(x, y);
        application.add(cardPanel);

        viewManagerModel.setState(welcomeView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
