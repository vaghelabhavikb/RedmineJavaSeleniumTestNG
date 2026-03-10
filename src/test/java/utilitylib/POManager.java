package utilitylib;

import org.openqa.selenium.WebDriver;

import po.AdminPage;
import po.CreateIssueForm;
import po.CreateProjectForm;
import po.CreateSpentTimeForm;
import po.CreateUserForm;
import po.IssuesTab;
import po.LandingPage;
import po.LoginPage;
import po.ProjectIssueInfoPage;
import po.ProjectPage;
import po.ProjectsQueryPage;
import po.SpentTimeTab;
import po.UsersQueryPage;

public class POManager {

	public WebDriver driver;
	
	//Common class
	private LoginPage loginPage;
	private LandingPage landingPage;
	
	//Users class
	private AdminPage adminPage;
	private UsersQueryPage usersQueryPage;
	private CreateUserForm createUserForm;
	
	//Projects class
	private	ProjectsQueryPage projectQueryPage;
	private CreateProjectForm createProjectForm;
	private ProjectPage projectPage;
	private IssuesTab issuesTab;
	private SpentTimeTab spentTimeTab;
	private CreateIssueForm createIssueForm;
	private CreateSpentTimeForm createSpentTimeForm;
	private ProjectIssueInfoPage projectIssueInfoPage;


	public POManager(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage loginPage() {
		if (loginPage == null) {
			loginPage = new LoginPage(driver);
		} // lazy
		return loginPage;
	}

	public LandingPage landingPage() {
		if (landingPage == null) {
			landingPage = new LandingPage(driver); // lazy
		}
		return landingPage;
	}

	public AdminPage adminPage() {
		if (adminPage == null) {
			adminPage = new AdminPage(driver); // lazy
		}
		return adminPage;
	}

	public UsersQueryPage usersQueryPage() {
		if (usersQueryPage == null) {
			usersQueryPage = new UsersQueryPage(driver); // lazy
		}
		return usersQueryPage;
	}

	public CreateUserForm createUserForm() {
		if (createUserForm == null) {
			createUserForm = new CreateUserForm(driver); // lazy
		}
		return createUserForm;
	}
	
	public ProjectsQueryPage projectQueryPage() {
		if (projectQueryPage == null) {
			projectQueryPage = new ProjectsQueryPage(driver); // lazy
		}
		return projectQueryPage;
	}
	
	public CreateProjectForm createProjectForm() {
		if (createProjectForm == null) {
			createProjectForm = new CreateProjectForm(driver); // lazy
		}
		return createProjectForm;
	}
	
	public ProjectPage projectPage() {
		if(projectPage == null) {
			projectPage = new ProjectPage(driver);
		}
		return projectPage;
	}
	
	public IssuesTab issuesTab() {
		if (issuesTab == null) {
			issuesTab = new IssuesTab(driver); // lazy
		}
		return issuesTab;
	}
	
	public SpentTimeTab spentTimeTab() {
		if(spentTimeTab == null) {
			spentTimeTab = new SpentTimeTab(driver);
		}
		return spentTimeTab;
	}
	
	public CreateIssueForm createIssueForm() {
		if(createIssueForm == null) {
			createIssueForm = new CreateIssueForm(driver);
		}
		return createIssueForm;
	}
	
	public CreateSpentTimeForm createSpentTimeForm() {
		if(createSpentTimeForm == null) {
			createSpentTimeForm = new CreateSpentTimeForm(driver);
		}
		return createSpentTimeForm;
	}
	
	public ProjectIssueInfoPage projectIssueInfoPage() {
		if(projectIssueInfoPage == null) {
			projectIssueInfoPage = new ProjectIssueInfoPage(driver);
		}
		return projectIssueInfoPage;
	}
	
}














