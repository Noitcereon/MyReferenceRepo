using System;
using TCPLib;
using TCPLib.Clients;

namespace TCPClientUI
{
    public class Program
    {
        static void Main(string[] args)
        {
            IClientWorker worker = new ClientWorker();
            worker.Start(3002);

            Console.ReadKey();
        }
    }
}
