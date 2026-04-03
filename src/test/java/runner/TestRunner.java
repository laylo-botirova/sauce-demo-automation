package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        plugin = {"pretty", "html:target/report.html"}
)
public class TestRunner  extends AbstractTestNGCucumberTests {
    @Parameters("browser")
    @org.testng.annotations.BeforeClass
    public void setBrowser(String browser) {
        System.setProperty("browser", browser);
    }
}