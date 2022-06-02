using System.Collections.Generic;
using System.Threading.Tasks;

namespace ABBLicensesAdministration.Common
{

    /// <summary>
    /// Interface to interact with the standard procedures.
    /// </summary>
    /// <typeparam name="T"></typeparam>
    public interface IDBCommon<T> where T : new()
    {
        Task<List<T>> GetAll();

        Task<T> GetOne(int id);

        Task<bool> Delete(int id);

        Task<bool> Create(T t);

        Task<bool> Update(int id, T t);
    }
}
