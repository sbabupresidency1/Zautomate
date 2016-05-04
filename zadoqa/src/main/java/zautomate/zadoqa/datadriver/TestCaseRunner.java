package zautomate.zadoqa.datadriver;

/**
 * Executing and add results in reports
 */
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import zautomate.zadoqa.ZadoReports;
import zautomate.zadoqa.enums.LogAs;
import zautomate.zadoqa.reports.CaptureScreen;
import zautomate.zadoqa.reports.CaptureScreen.ScreenshotOf;
import zautomate.zadoqa.util.CommandUtils;

public class TestCaseRunner {

	public static void exectuteTestCase(WebDriver driver, List<CaseStep> steps) throws Exception {
		Iterator<CaseStep> stepIterator = steps.iterator();

		while (stepIterator.hasNext()) {
			CaseStep eachStep = stepIterator.next();
			CommandUtils util = new CommandUtils();
			WebElement element = null;

			if (eachStep.getOrLocator()!=null) {		
				try
				{
					element = util.findElement(driver, eachStep.getLocateBy(),
							eachStep.getOrLocator());
				}
				catch(NoSuchElementException exception)
				{
					throw exception;
				}
			}
			Object returnObj = util.executeAction(driver, element, eachStep.getAction(),
					eachStep.getInputData(),eachStep.getStepNo(),eachStep.getReferenceStep());	    
			//ZadoReports.add(eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString(returnObj, ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			ZadoReports.add(eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString(returnObj, ""),LogAs.PASSED, null);
		}
	}
}