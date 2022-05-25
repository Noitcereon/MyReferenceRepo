using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using PracticeRestLib;
using PracticeRestService.Managers;

namespace PracticeRestService.Controllers
{
    [ApiController]
    [Route("api/localPizzas/")]
    public class PizzasController : ControllerBase
    {
        private readonly PizzaManager _manager = new PizzaManager();

        [HttpGet]
        public IList<Pizza> Get()
        {
            return _manager.GetAll();
        }

        [HttpGet]
        [Route("{number}")]
        public Pizza GetOne(int number)
        {
            return _manager.GetOne(number);
        }
        [HttpGet]
        [Route("description/{substring}")]
        public IEnumerable<Pizza> GetFromSubstring(string substring)
        {
            return _manager.GetFromSubstring(substring.ToLower());
        }

        [HttpPost]
        public String Post([FromBody]Pizza newPizza)
        {
            return _manager.Post(newPizza);
        }
        [HttpPut]
        [Route("{number}")]
        public String Put(Pizza updatedPizza, int number)
        {
            return _manager.Put(updatedPizza, number);
        }

        [HttpDelete]
        [Route("{number}")]
        public String Delete(int number)
        {
            return _manager.Delete(number);
        }
    }
}
