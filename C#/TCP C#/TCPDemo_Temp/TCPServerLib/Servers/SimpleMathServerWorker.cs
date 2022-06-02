using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace TCPLib.Servers
{
    public class SimpleMathServerWorker : IServerWorker
    {
        /// <summary>
        /// Starts a TCP server on port 3001.
        /// </summary>
        public void Start()
        {
            // Create server
            // IP of own computer, port is type of application.
            TcpListener server = new TcpListener(IPAddress.Loopback, 3001);
            server.Start();
            Console.WriteLine("SimpleMath Server ready.");
            while (true)
            {
                // Waits for a client to call.
                TcpClient tempSocket = server.AcceptTcpClient();

                Task.Run(() =>
                {
                    DoClient(tempSocket);
                });
            }
        }

        protected void DoClient(TcpClient socket)
        {
            NetworkStream ns = socket.GetStream();
            StreamReader streamReader = new StreamReader(ns);
            StreamWriter streamWriter = new StreamWriter(ns);

            // Read text from client
            string str = streamReader.ReadLine();

            string[] splitStr = str?.Split(' ');
            List<int> numbers = new List<int>();

            if (splitStr != null)
                foreach (var item in splitStr)
                {
                    if (Regex.IsMatch(item, "\\d"))
                    {
                        numbers.Add(int.Parse(item));
                    }
                } 

            int output = 0;
            foreach (var number in numbers)
            {
                Console.WriteLine($"{number} added.");
                output += number;
            }

            Console.WriteLine($"Result: {output}");
            // Write to back to the client
            streamWriter.WriteLine($"Result: {output}");
            streamWriter.Flush(); // empties buffer

            socket.Close();
        }
    }
}
