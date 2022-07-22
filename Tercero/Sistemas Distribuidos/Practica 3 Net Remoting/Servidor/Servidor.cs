using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting;
using System.Text;
using System.Threading.Tasks;

namespace Servidor {
    class Servidor {
        static void Main(string[] args) {
            RemotingConfiguration.Configure("Servidor.exe.config", false);
            Console.WriteLine("Esperando llamadas Remotas...");
            Console.WriteLine("Pulsa Enter para Salir..");
            Console.ReadLine();
        }
    }
}
