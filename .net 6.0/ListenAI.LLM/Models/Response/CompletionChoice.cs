using Newtonsoft.Json;

namespace ListenAI.LLM.Models.Response; 

public class CompletionChoice {
    /// <summary>
    /// 会话结束原因
    /// </summary>
    [JsonProperty("finish_reason")]
    public string FinishReason { get; set; }
    
    /// <summary>
    /// 消息排序
    /// </summary>
    [JsonProperty("index")]
    public int Index { get; set; }
    
    /// <summary>
    /// 消息文本内容
    /// </summary>
    [JsonProperty("message")]
    public SparkMessage Message { get; set; }
}