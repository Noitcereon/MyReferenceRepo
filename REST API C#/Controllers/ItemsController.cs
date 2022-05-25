using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using PracticeRestLib;
using PracticeRestService.Managers;

namespace PracticeRestService.Controllers
{
    [ApiController]
    [Route("api/localItems/")]
    public class ItemsController : ControllerBase
    {
        private readonly ItemManager _manager = new ItemManager();

        [HttpGet]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public IActionResult GetAll()
        {
            IList<Item> items = _manager.GetAll();

            if (items == null)
            {
                return NotFound("No itemlist found.");
            }
            if (items.Count > 0)
            {
                return Ok(items);
            }

            return NotFound("List of items is empty.");
        }

        [HttpGet]
        [Route("{id}")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public IActionResult GetOne(int id)
        {
            if (_manager.GetOne(id) != null)
            {
                return Ok(_manager.GetOne(id));
            }

            return NotFound($"Item with id {id} does not exist.");
        }
        [HttpGet]
        [Route("Name/{substring}")]
        public IEnumerable<Item> GetByNameSubstring(string substring)
        {
            return _manager.GetByNameSubstring(substring);
        }
        [HttpGet]
        [Route("Quality/{substring}")]
        public IEnumerable<Item> GetByQualitySubstring(string substring)
        {
            return _manager.GetByQualitySubstring(substring);
        }

        [HttpGet]
        [Route("search")]
        public IEnumerable<Item> GetWithFilter([FromQuery] FilterItem filter)
        {
            return _manager.GetWithFilter(filter);
        }

        [HttpPost]
        public String Post([FromBody] Item newItem)
        {
            return _manager.Post(newItem);
        }
        [HttpPut]
        [Route("{id}")]
        public String Put(Item updatedItem, int id)
        {
            return _manager.Put(updatedItem, id);
        }

        [HttpDelete]
        [Route("{id}")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public IActionResult Delete(int id)
        {
            if (_manager.GetOne(id) != null)
            {
                return Ok(_manager.Delete(id));
            }

            return NotFound("Cannot delete a value that does not exist.");
        }
    }
}
