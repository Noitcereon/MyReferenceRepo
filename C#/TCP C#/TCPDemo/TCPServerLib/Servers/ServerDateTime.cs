using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace TCPLib.Servers
{
    public class ServerDateTime : IServerWorker
    {
        public void Start()
        {
            TcpListener server = new TcpListener(IPAddress.Loopback, 3003);
            server.Start();
            Console.WriteLine("Datetime server ready.");
            while (true)
            {
                TcpClient socket = server.AcceptTcpClient();
                Task.Run(() => HandleClient(socket));
            }
        }

        private void HandleClient(TcpClient socket)
        {
            NetworkStream ns = socket.GetStream();
            StreamReader sr = new StreamReader(ns);
            StreamWriter sw = new StreamWriter(ns);

            string clientMsg = sr.ReadLine();
            Regex pattern = new Regex(@"([0-9]\d{3}|\d{2})-\d{2}-\d{2} [0-2]\d{1}:[0-5][0-9]", RegexOptions.CultureInvariant);
            if (pattern.IsMatch(clientMsg))
            {
                DateTime output = DateTime.ParseExact(clientMsg, "yyyy-MM-dd HH:mm", CultureInfo.InvariantCulture);
                sw.WriteLine($"{output.ToShortDateString()} {output.ToShortTimeString()}");

            }
            else
            {
                sw.WriteLine("Unsuccessful.");
            }

            sw.Flush();

            socket.Close();
        }
    }
}
