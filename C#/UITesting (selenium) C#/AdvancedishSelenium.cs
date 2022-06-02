using System;
using DRMusicLib;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Support.UI;

namespace UISeleniumTest
{
    [TestClass]
    public class UnitTest1
    {
        //private static readonly string Directory = @"C:\Users\mtlau\source\repos\DRMusicRecordsREST\UISeleniumTest\Drivers";
        private static readonly string Directory = @"F:\visual_studio_projects\repos\3_semester\DRMusicRecordsREST\UISeleniumTest\Drivers";
        private const string LocalUrl = "http://localhost:3000/";
        private const string AzureUrl = "https://drmusicwebapp.azurewebsites.net";

        private static IWebDriver _driver;
        [ClassInitialize]
        public static void Initialize(TestContext context)
        {
            //_driver = new ChromeDriver(Directory);
            _driver = new FirefoxDriver(Directory);
            _driver.Navigate().GoToUrl(LocalUrl);

        }

        [ClassCleanup]
        public static void End()
        {
            _driver.Dispose();
        }

        [TestMethod]
        public void WebpageValidationTest()
        {
            Assert.AreEqual("DR Music Records", _driver.Title);
        }
        [TestMethod]
        public void GetAllTest()
        {
            
            IWebElement GetAllBtn = _driver.FindElement(By.Id("GetAllRecordsBtn"));
            IWebElement Content = _driver.FindElement(By.Id("GetAllUL"));
            
            Assert.AreEqual("",Content.Text);

            GetAllBtn.Click();

            Assert.AreNotEqual("",Content.Text);

        }

        [TestMethod]
        public void SearchByTest()
        {
            IWebElement content = _driver.FindElement(By.Id("GetAllUL"));
            var searchOption = _driver.FindElement(By.Id("searchOption"));
            var searchInput = _driver.FindElement(By.Id("searchInput"));
            var searchBtn = _driver.FindElement(By.Id("searchBtn"));

            searchInput.SendKeys("Uprising");
            var optionElement = new SelectElement(searchOption);
            optionElement.SelectByValue("Title");

            Assert.AreEqual("", content.Text);
            searchBtn.Click();
            Assert.AreNotEqual("", content.Text);
        }

        [TestMethod]
        public void PostTest()
        {
            IWebElement artistInput = _driver.FindElement(By.Id("InpArtist"));
            IWebElement titleInput = _driver.FindElement(By.Id("InpTitle"));
            IWebElement durationInput = _driver.FindElement(By.Id("InpDuration"));
            IWebElement yearInput = _driver.FindElement(By.Id("InpYear"));

            artistInput.SendKeys("Ylvis");
            titleInput.SendKeys("Stonehenge");
            durationInput.SendKeys("534");
            yearInput.SendKeys("2012");

            IWebElement addBtn = _driver.FindElement(By.Id("PostBtn"));
            addBtn.Click();

            WebDriverWait wait = new WebDriverWait(_driver, TimeSpan.FromSeconds(10));
            IWebElement successMessage = wait.Until(d =>d.FindElement(By.Id("addSuccessful")));

            Assert.IsTrue(successMessage.Displayed);
        }

        [TestMethod]
        public void DeleteTest()
        {
            IWebElement artistInput = _driver.FindElement(By.Id("deleteArtist"));
            IWebElement titleInput = _driver.FindElement(By.Id("deleteTitle"));

            artistInput.SendKeys("Ylvis");
            titleInput.SendKeys("Stonehenge");

            IWebElement deleteBtn = _driver.FindElement(By.Id("deleteBtn"));
            deleteBtn.Click();

            WebDriverWait wait = new WebDriverWait(_driver, TimeSpan.FromSeconds(10));
            IWebElement successMessage = wait.Until(d => d.FindElement(By.Id("deleteSuccessful")));

            Assert.IsTrue(successMessage.Displayed);
        }

        [TestMethod]
        public void UpdateTest()
        {
            IWebElement artistToUpdate = _driver.FindElement(By.Id("updateArtist"));
            IWebElement titleToUpdate = _driver.FindElement(By.Id("updateTitle"));

            artistToUpdate.SendKeys("Ylvis No!");
            titleToUpdate.SendKeys("Ylvis Yes! Ylvis Always Yes!");

            var artistInput = _driver.FindElement(By.Id("updateInpArtist"));
            var titleInput = _driver.FindElement(By.Id("updateInpTitle"));
            var durationInput = _driver.FindElement(By.Id("updateInpDuration"));
            var releaseYearInput = _driver.FindElement(By.Id("updateInpYear"));

            artistInput.SendKeys("Ylvis No!");
            titleInput.SendKeys("Ylvis Yes! Ylvis Always Yes!");
            durationInput.SendKeys("353");
            releaseYearInput.SendKeys("2010");

            IWebElement updateBtn = _driver.FindElement(By.Id("UpdateBtn"));
            updateBtn.Click();

            WebDriverWait wait = new WebDriverWait(_driver, TimeSpan.FromSeconds(20));
            wait.Until(d => d.FindElement(By.Id("updateSuccessful")));
            IWebElement successMessage = _driver.FindElement(By.Id("updateMessage"));

            //Updated: ${ updateArtist} - ${ updateTitle} => ${ this.updateMusicRecord.artist} - ${ this.updateMusicRecord.title}
            Assert.AreEqual("Updated: Ylvis No! - Ylvis Yes! Ylvis Always Yes! => Ylvis No! - Ylvis Yes! Ylvis Always Yes!",
                successMessage.Text);
        }
    }
}
