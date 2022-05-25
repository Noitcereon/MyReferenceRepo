using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using RestApi.Managers;

namespace UnitTesting
{
    [TestClass]
    public class ItemTest
    {
        private ItemManager _manager;
        private int _itemCountBeforeTest;

        [TestInitialize]
        private void Init()
        {
            _manager = new ItemManager();
            _itemCountBeforeTest = _manager.GetAll().Count;
        }

        [TestMethod]
        public void GetAllTest()
        {
            int actualCount = _manager.GetAll().Count;

            Assert.AreEqual(_itemCountBeforeTest, actualCount);

            Assert.IsInstanceOfType(_manager.GetAll(), typeof(List<Item>));
        }
    }
}
