using ListenAI.LLM;
using ListenAI.LLM.Models;

namespace Tester
{
    internal class Program
    {
        static void Main(string[] args)
        {
            new TesterProgram().Run();
        }
    }

    internal class TesterProgram {
        public void Run() {
            var spark = new SparkInstance(new SparkOptions() {
                Token = "36d8ebc4-9a7e-4479-831a-a2dc0c282b3e"
            });

            var reqOptions = new CompletionOptions();
            while (true) {
                Console.Write("===> ");
                var prompt = Console.ReadLine();
                if (prompt == "[clear]") {
                    reqOptions = new CompletionOptions();
                    ConsoleOutput("=== New conversation!");
                    continue;
                }

                reqOptions.AddMessage(prompt, SparkMessageRole.User);
                ConsoleOutput("===> Requesting...");

                try {
                    var resp = spark.Conversations(reqOptions);
                    ConsoleOutput($"<=== {resp.Choices[0].Message.Content}");
                    reqOptions.AddMessage(resp.Choices[0].Message);
                } catch (SparkException spex) {
                    ConsoleOutput($"<=== Exception: ({spex.Error.Code}) {spex.Error.Details}");
                }
            }
        }

        private void ConsoleOutput(string str) {
            Console.WriteLine($"[{DateTime.UtcNow}] {str}");
        }
    }
}