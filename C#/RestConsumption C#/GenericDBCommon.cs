using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace ABBLicensesAdministration.Common
{
    /// <summary>
    /// Generic database caller, used for most DB operations.
    /// </summary>
    /// <typeparam name="T"></typeparam>
    public class GenericDBCommon<T> : IDBCommon<T> where T : new()
    {

        protected readonly String TypeName;
        private const string URI = "https://abbrestapi.azurewebsites.net/";
        //private const string URI = "http://localhost:56519/"; // For Local REST API Testing 

        public GenericDBCommon()
        {
            T t = new T();
            TypeName = t.GetType().Name;
        }


        public async Task<List<T>> GetAll()
        {
            List<T> liste = new List<T>();

            using (HttpClient client = new HttpClient())
            {
                String json = await client.GetStringAsync(new Uri(URI + TypeName)).ConfigureAwait(false);
                liste = JsonConvert.DeserializeObject<List<T>>(json);
            }

            return liste;
        }

        public async Task<T> GetOne(int id)
        {
            T t = new T();

            using (HttpClient client = new HttpClient())
            {
                String json = await client.GetStringAsync(new Uri(URI + TypeName + "/" + id)).ConfigureAwait(false);
                t = JsonConvert.DeserializeObject<T>(json);
            }

            return t;
        }

        public async Task<bool> Delete(int id)
        {
            bool success = false;

            using (HttpClient client = new HttpClient())
            {
                HttpResponseMessage response = await client.DeleteAsync(new Uri(URI + TypeName + "/" + id)).ConfigureAwait(false);
                if (response.IsSuccessStatusCode)
                {
                    String json = await response.Content.ReadAsStringAsync();
                    success = JsonConvert.DeserializeObject<bool>(json);
                }
            }

            return success;
        }

        public async Task<bool> Create(T t)
        {
            bool success = false;

            using (HttpClient client = new HttpClient())
            {
                String json = JsonConvert.SerializeObject(t);
                StringContent content = new StringContent(json, Encoding.UTF8, "application/json");


                HttpResponseMessage response = await client.PostAsync(new Uri(URI + TypeName), content).ConfigureAwait(false);
                if (response.IsSuccessStatusCode)
                {
                    String jsonRes = await response.Content.ReadAsStringAsync().ConfigureAwait(false);
                    success = JsonConvert.DeserializeObject<bool>(jsonRes);
                }
                content.Dispose();
            }

            return success;
        }

        public async Task<bool> Update(int id, T t)
        {
            bool success = false;

            using (HttpClient client = new HttpClient())
            {
                String json = JsonConvert.SerializeObject(t);
                StringContent content = new StringContent(json, Encoding.UTF8, "application/json");

                HttpResponseMessage response = await client.PutAsync(new Uri(URI + TypeName + "/" + id), content).ConfigureAwait(false);
                if (response.IsSuccessStatusCode)
                {
                    String jsonRes = await response.Content.ReadAsStringAsync().ConfigureAwait(false);
                    success = JsonConvert.DeserializeObject<bool>(jsonRes);
                }
                content.Dispose();
            }

            return success;
        }
    }
}
