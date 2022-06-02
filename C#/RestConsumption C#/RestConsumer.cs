using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using PracticeRestLib;

namespace RestConsumptionConsoleUI
{
    public class RestConsumer
    {
        private const string URI = "http://noitrest.azurewebsites.net/api/localItems/";

        public async Task<IList<Item>> GetAll()
        {
            using (HttpClient client = new HttpClient())
            {
                string content = await client.GetStringAsync(new Uri(URI));
                IList<Item> items = JsonConvert.DeserializeObject<IList<Item>>(content);

                return items;
            }
        }
        public async Task<Item> GetOne(int id)
        {
            using (HttpClient client = new HttpClient())
            {
                HttpResponseMessage response = await client.GetAsync(new Uri(URI + id));
                string jsonContent = await response.Content.ReadAsStringAsync();

                if (response.IsSuccessStatusCode)
                {
                    Item item = JsonConvert.DeserializeObject<Item>(jsonContent);
                    return item;
                }

                throw new KeyNotFoundException($"Status code: {response.StatusCode}\n Message: {jsonContent}");
            }
        }

        public async Task<string> Post(Item item)
        {
            using (HttpClient client = new HttpClient())
            {
                string json = JsonConvert.SerializeObject(item);
                StringContent content = new StringContent(json, Encoding.UTF8, "application/json");
                HttpResponseMessage response = await client.PostAsync(new Uri(URI), content);

                if (response.IsSuccessStatusCode)
                {
                    return $"Item with id {item.Id} added.";
                }
                throw new Exception($"Something went wrong during Post(Item item). " +
                                    $"Statuscode: {response.StatusCode}");
            }
        }

        public async Task<string> Put(int id, Item item)
        {
            using (HttpClient client = new HttpClient())
            {
                string json = JsonConvert.SerializeObject(item);
                StringContent content = new StringContent(json, Encoding.UTF8, "application/json");
                HttpResponseMessage response = await client.PutAsync(URI + id, content);
                if (response.IsSuccessStatusCode)
                {
                    return $"Updated item with id {id}.";
                }
                throw new Exception($"Status code: {response.StatusCode}");
            }
        }

        public async Task<string> Delete(int id)
        {
            using (HttpClient client = new HttpClient())
            {
                HttpResponseMessage response = await client.DeleteAsync(URI + id);
                if (response.IsSuccessStatusCode)
                {
                    return $"Deleted item nr. {id}";
                }
                throw new Exception($"Status code: {response.StatusCode}");
            }
        }
    }
}
