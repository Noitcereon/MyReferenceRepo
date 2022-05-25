using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Support.UI;

namespace SeleniumTesting
{
    [TestClass]
    public class UITest
    {
        private static IWebDriver _driver;

        [ClassInitialize]
        public static void BeforeTests(TestContext context)
        {
            // Choose which browser/driver to test with.
            _driver = new ChromeDriver { Url = "http://localhost:3000" };
            //_driver = new FirefoxDriver { Url = "http://localhost:3000" };
        }

        [ClassCleanup]
        public static void AfterTests()
        {
            _driver.Dispose();
        }

        [TestMethod]
        public void SubmitItem()
        {
            _driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10); // the test will wait for up to 10 seconds, if it can't find the element.
            Assert.AreEqual("Covid Record Creation", _driver.Title);

            IWebElement inputElement = _driver.FindElement(By.Id("[input-field-id]"));
            IWebElement button = _driver.FindElement(By.Id("[id-of-button]"));
            IWebElement result = _driver.FindElement(By.Id("id-of-result-tag"));
            inputElement.SendKeys("Yeet!");
            button.Submit();
            string expectedText = "Greetings Yeet!";
            Assert.AreEqual(expectedText, result.Text);
        }
     
    }
}
