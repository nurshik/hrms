package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Dashboard.feature",

        //glue is where we can find implementation for gherkin steps
        //we provide the path of package for steps
        glue= "steps",

        //if we set dry run to true, it will quickly scan all gherkin steps are implemented
        //if it is true, then no actual execution happens
        dryRun = false,

        //it means that console output for the cucumber test is easily readable
        //it will remove unreadable characters
        monochrome = true,

        // if strict is set to true then at the time of of execution if cucumber encounters any undefined
        // steps, it will give us an error and stops execution , also, it gives us a snippet of the code failed
        //strict = true

        tags="@dashboardtabs",
        // tags will identify the scenarios based on the tags we will provide such as @smoke, @regression, etc
        //we can add multiple tags in the runner class to execute scenarios belong to different tags, such as and, or
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json","rerun:target/failed.txt"}

)
public class RegressionRunner {

}
