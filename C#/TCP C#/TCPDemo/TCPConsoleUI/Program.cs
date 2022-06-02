using System;
using TCPLib;
using TCPLib.Servers;

namespace TCPServerUI
{
    public class Program
    {
        static void Main(string[] args)
        {
            IServerWorker serverWorker = new ServerDateTime();
            serverWorker.Start();

            Console.ReadLine();
        }
    }
}
