using Newtonsoft.Json;

namespace ListenAI.LLM.Models.Response; 

public class SparkUsage {
    [JsonProperty("completion_tokens")]
    public int CompletionTokens { get; set; }
    
    [JsonProperty("prompt_tokens")]
    public int PromptTokens { get; set; }
    
    [JsonProperty("question_tokens")]
    public int QuestionTokens { get; set; }
    
    [JsonProperty("total_tokens")]
    public int TotalTokens { get; set; }
}