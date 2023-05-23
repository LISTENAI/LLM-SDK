using System.Net;
using ListenAI.LLM.Models;
using ListenAI.LLM.Models.Response;
using Newtonsoft.Json;

namespace ListenAI.LLM {
    public class SparkInstance {
        private readonly SparkOptions _options;

        public SparkInstance(SparkOptions options) {
            _options = options;
        }

        public CompletionResponse Conversations(CompletionOptions options) {
            var resp = Helpers.HttpOperation(Constants.UrlFactory.Completions, HttpMethod.Post,
                options.ToString(), "application/json", _options.Token);
            try {
                if (resp.Code != HttpStatusCode.OK) {
                    var err = JsonConvert.DeserializeObject<SparkError>(resp.Content);
                    throw new SparkException(err);
                }
                
                var result = JsonConvert.DeserializeObject<CompletionResponse>(resp.Content);
                return result;
            }
            catch (Exception ex) when (!(ex is SparkException)) {
                throw new SparkException(new SparkError() {
                    StatusCode = 402,
                    Code = "FST_ERR_OTHER",
                    Details = ex.Message,
                    Message = "SDK Error"
                });
            }
        }
    }
}