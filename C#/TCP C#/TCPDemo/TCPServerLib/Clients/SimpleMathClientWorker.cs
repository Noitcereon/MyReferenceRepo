using System;
using System.IO;
using System.Net.Sockets;

namespace TCPLib.Clients
{
    public class SimpleMathClientWorker : IClientWorker
    {
        public void Start()
        {
            TcpClient client = new TcpClient("localhost", 3001);

            DoClient(client);
        }

        public void Start(int port)
        {
            TcpClient client = new TcpClient("localhost", port);

            DoClient(client);
        }

        private void DoClient(TcpClient client)
        {
            NetworkStream ns = client.GetStream();
            StreamReader sr = new StreamReader(ns);
            StreamWriter sw = new StreamWriter(ns);

            sw.WriteLine("ADD 5 8");
            sw.Flush();

            Console.WriteLine($"Server response: {sr.ReadLine()}");

            client.Close();
        }
    }
}
