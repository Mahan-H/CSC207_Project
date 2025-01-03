### For each Principle of Universal Design, write 2-3 sentences — or point form notes — explaining which features of your program adhere to that principle.

Equitable Use:​

Anyone who has basic computer knowledge​

Target Audience:​

Anyone who is looking to gain insight from their financial information​

Low Effort and Simple and Intuitive:

It is low effort and efficient for people with a low attention span

**S:** The PieChartUtility class is solely responsible for creating and displaying pie charts. It does not handle transactions, user data, or presentation logic, adhering to SRP. The way we have laid out our use cases to adhere to clean architecture also shows this. For example, the Views for each use case are responsible for making the view page (should only change for changing the display) 

**O:** The PlaidDataAccessObject cannot be modified since it organizes functionality into methods with specific responsibilities, but it can be extended to support accessing different Plaid data. (fetching transactions,​ creating link tokens, etc). The entity package cannot be modified (as that will change what the entity represents), but we can add more entities to that package as our project expands. 

**L:** The GrabTransactionController returns a GrabTransactionOutputData, which can be changed to a String object without breaking the existing code. This means that the code will still run. The Controller is designed to work with different kinds of outputs so long as they are similar in behaviour. If what GrabTransactionController returned was changed, from GrabTransactionOutputData to a list of transactions, the rest of the code would not need to be changed. The rest of the code would operate like clockwork and this would be attributed to the controller not depending on a specific type. This means that it can work with general structures that can be changed without a massive failure.

**I:** The inclusion of several interfaces within our application allows for compartmentalization of its overall functioning. Interfaces such as the VerifyInputBoundary, LoginInputBoundary, and SignupInputBoundary are all intended to work together to complete different parts of the user handling process. Thus, preventing a case where one class is required to implement methods that it itself does not utilize. Streamlining interactions between related files also allows segregation of interfaces. For example, the VerifyInteractor only interacts with files related to Verification, such as the VerifyInputBoundary and VerifyOutputBoundary, and not files involved in the signup or login process. Even then, the three aforementioned files have designated operations that could have potentially been amalgamated into one singular file. This is done to avoid unnecessary coupling and creates a system with easier maintenance.

**D:** The AppBuilder class displays connections between high-level modules, such as Controllers and low-level modules like Interactors through abstract classes. Instead of having higher level modules feature concrete implementations, the injection of dependencies is conducted in the initialization process. This can ensure flexibility and modularity. For example, the VerifyController depends on VerifyInputBoundary, allowing the VerifyInteractor to be modified without alterations of the controller. This is another concept that improves the quality of the development process.


### Write a paragraph (3-6 sentences) about who you would market your program towards, if you were to sell or license your program to customers. This could be a specific category of users, such as "students", or more vague, such as "people who like games". Try to give a bit more detail along with the category.

The program would be best marketed towards young adults and adolescents, specifically university students. It would serve as a way for them to aggregate their financial data and create limitations for themselves. For many, university is a very crucial and expensive time. The latter point means they must retain their funds as carefully as possible, as not doing so can make a substantial difference. A majority of students do not have disposable funds, so a behaviour like budgeting would prove to be extremely helpful. An app such as Osiris, which can help students reduce unnecessary expenses and be more financially aware of what they expend funds on, can save them from inconveniences caused by a lack of funds. This could go as far as preventing them from skipping meals due to unavailability of funds, or even avoiding purchasing textbooks. It would prove to not only be a financially assisting app, but an app that indirectly benefits health and academics as well.


### Write a paragraph about whether or not your program is less likely to be used by certain demographics. Your discussion here should be informed by the content of our embedded ethics modules this term.

This app is most likely to be utilized by those who are not well versed in finance, such as students. It is made to be a simple application that allows users to understand their finances and figure out a solution by seeing everything in one place. Since the app does not require much technical knowledge, besides knowing one’s banking login information and creating and logging in with their login information for the app, it wouldn’t be the case where this app couldn't be inclusive of all populations. This app does however have a lack of information, since it is made to be simple, thus it wouldn’t be meant for those who are knowledgeable in finance and prefer to have much more intricate features when managing their finances. For example, having a way to make investments or the best way to collect interest on high profile accounts. This app was made with consideration of the people. By the people, we mean everybody. Not everything is obvious to everybody, so making it so that people can easily see the basic things they need to see was taken into consideration with this app. We even included a system to display diagrams so that people who prefer visualization over specifics could grasp a deeper mental understanding of certain statistics. For example, one may look at their spending and think nothing of it, but looking at a chart that shows equal red and blue should be alarming, since red would mean bad and blue would mean good.
