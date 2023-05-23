using Newtonsoft.Json;

namespace ListenAI.LLM.Models.Response; 

public class CompletionResponse {
    /// <summary>
    /// AI回复内容
    /// </summary>
    [JsonProperty("choices")]
    public List<CompletionChoice> Choices { get; set; }
    
    /// <summary>
    /// 回复创建时间
    /// </summary>
    [JsonProperty("created")]
    public int CreatedAt { get; set; }
    
    /// <summary>
    /// 回复 ID
    /// </summary>
    [JsonProperty("id")]
    public string Id { get; set; }

    /// <summary>
    /// 回复内容类型 (目前仅限 chat.completion)
    /// </summary>
    [JsonProperty("object")]
    public string Object { get; set; }
    
    /// <summary>
    /// 用量统计
    /// </summary>
    [JsonProperty("usage")]
    public SparkUsage Usage { get; set; }
}