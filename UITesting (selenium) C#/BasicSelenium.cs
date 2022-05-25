using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;

namespace SimpleTypeScriptAppTest
{
    [TestClass]
    public class UnitTest1
    {
        private const string DriverFolder = @"F:\visual_studio_projects\repos\3_semester\simpleTypeScriptApp\drivers";

        private static IWebDriver _driver;

        [ClassInitialize]
        public static void BeforeTests(TestContext context)
        {
            _driver = new ChromeDriver(); // fast
            //_driver = new FirefoxDriver(); // sluggish
        }

        [ClassCleanup]
        public static void AfterTests()
        {
            _driver.Dispose();
        }

        [TestMethod]
        public void GreetingAndLocationTest()
        {
            _driver.Navigate().GoToUrl("http://localhost:3000");
            string title = _driver.Title;
            Assert.AreEqual("Simple TypeScript", title);

            IWebElement nameInput = _driver.FindElement(By.Id("nameInput"));
            nameInput.SendKeys("Gandalf");

            IWebElement nameSubmit = _driver.FindElement(By.Id("nameSubmit"));
            nameSubmit.Click();

            IWebElement outputField = _driver.FindElement(By.Id("helloMessage"));
            string expectedGreeting = "Hello Gandalf";
            string actualGreeting = outputField.Text;

            Assert.AreEqual(expectedGreeting, actualGreeting);
        }
    }
}
