using Newtonsoft.Json;

namespace ListenAI.LLM.Models.Response; 

public class SparkError {
    [JsonProperty("statusCode")]
    public int StatusCode { get; set; }
    
    [JsonProperty("code")]
    public string Code { get; set; }
    
    [JsonProperty("error")]
    public string Message { get; set; }
    
    [JsonProperty("message")]
    public string Details { get; set; }
}