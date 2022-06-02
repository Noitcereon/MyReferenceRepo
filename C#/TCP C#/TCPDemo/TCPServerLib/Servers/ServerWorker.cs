using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Threading.Tasks;

namespace TCPLib.Servers
{
    public class ServerWorker : IServerWorker
    {
        /// <summary>
        /// Starts a TCP server on port 7.
        /// </summary>
        public virtual void Start()
        {
            // Create server
            // IP of own computer, port is type of application, which in this case is an echo server, hence port 7.
            TcpListener server = new TcpListener(IPAddress.Loopback, 7); // port 7 = echo server
            server.Start();
            Console.WriteLine("Server ready.");
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

        /// <summary>
        /// Starts a TCP server on the specified port.
        /// </summary>
        /// <param name="port">The specified port eg. '80'</param>
        public virtual void Start(int port)
        {
            // Create server
            // IP of own computer, port is type of application, which in this case is an echo server, hence port 7.
            TcpListener server = new TcpListener(IPAddress.Loopback, port); // port 7 = echo server
            server.Start();
            Console.WriteLine("Server ready.");
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

        protected virtual void DoClient(TcpClient socket)
        {
            NetworkStream ns = socket.GetStream();
            StreamReader streamReader = new StreamReader(ns);
            StreamWriter streamWriter = new StreamWriter(ns);

            // Read text from client
            string str = streamReader.ReadLine();

            Console.WriteLine($"Server received content: {str?.ToUpper()}");
            Console.WriteLine($"Word count: { HelperMethods.CountWordsInString(str) }");

            // Write to back to the client
            streamWriter.WriteLine(str);
            streamWriter.Flush(); // empties buffer

            socket.Close();
        }
    }
}
