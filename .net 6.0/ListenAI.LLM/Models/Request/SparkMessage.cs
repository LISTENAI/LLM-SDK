using Newtonsoft.Json;

namespace ListenAI.LLM.Models; 

public class SparkMessage {
    /// <summary>
    /// [必填] 角色 (user = 用户的提问, assistant = AI 的回复)
    /// </summary>
    [JsonIgnore]
    public SparkMessageRole Role { get; set; }

    [JsonProperty("role")]
    private string RoleRaw {
        get => Role.Value;
        set {
            Role = new SparkMessageRole(value);
        }
    }

    /// <summary>
    /// [必填] 该角色的对话内容
    /// </summary>
    [JsonProperty("content")]
    public string Content { get; set; }
}