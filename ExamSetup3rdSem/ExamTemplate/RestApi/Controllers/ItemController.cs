using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using RestApi.Managers;

namespace RestApi.Controllers
{
    [ApiController]
    [Route("api/[controller]/")]
    public class ItemController : ControllerBase
    {
        private readonly ItemManager _manager = new ItemManager();

        [HttpGet]
        [ProducesResponseType(StatusCodes.Status200OK)]
        public IActionResult GetAll()
        {
            List<Item> items = _manager.GetAll();

            if (items.Count <= 0) return Ok("Request successful, but the collection is empty.");

            return Ok(items);
        }
    }
}
