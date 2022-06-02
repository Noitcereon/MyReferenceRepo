using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Threading.Tasks;

namespace TCPLib.Servers
{
    public class ServerMathWorker : IServerWorker
    {
        public void Start()
        {
            TcpListener server = new TcpListener(IPAddress.Loopback, 3002);
            server.Start();
            Console.WriteLine("MathServer ready.");
            while (true)
            {
                TcpClient tempSocket = server.AcceptTcpClient();
                Task.Run(() =>
                {
                    HandleClient(tempSocket);
                });
            }
        }


        private void HandleClient(TcpClient socket)
        {
            NetworkStream ns = socket.GetStream();
            StreamWriter sw = new StreamWriter(ns);
            StreamReader sr = new StreamReader(ns);

            string clientMsg = sr.ReadLine();
            Console.WriteLine($"Request: {clientMsg}");

            List<double> numbers = new List<double>();
            var splitStr = clientMsg?.Split(" ");
            if (splitStr == null) return;
            for (int i = 0; i < splitStr.Length; i++)
            {
                if (i == 0)
                {
                    if (splitStr[i].ToLower() != "ADD".ToLower()) break;
                }
                else
                {
                    numbers.Add(Double.Parse(splitStr[i], new CultureInfo("da-DK")));
                }
            }

            string response = $"Result: {numbers.Sum()}";
            Console.WriteLine($"Response: {response}");

            sw.WriteLine(response);
            sw.Flush();

            socket.Close();
        }
    }
}
