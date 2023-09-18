package StepDefinitions;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListeners implements  ITestListener{

	 @Override
	    public void onTestFailure(ITestResult result) {
		 if (result.getMethod().getMethodName().equals("user launches the deutsche bank website$")) {
			 System.out.println("Too Many Request hence quiting the driver........");		            
					 System.exit(1);
			 
	    }
	 }
}
