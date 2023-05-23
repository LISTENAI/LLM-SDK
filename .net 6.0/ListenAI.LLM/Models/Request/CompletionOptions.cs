using ListenAI.LLM.Models.Response;
using Newtonsoft.Json;

namespace ListenAI.LLM.Models; 

public class CompletionOptions {
    /// <summary>
    /// [必填] 会话和提问 (需要自行调用 AddMessage 拼接对话历史信息，数组最后是最新的用户提问)
    /// </summary>
    [JsonRequired, JsonProperty("messages")]
    public List<SparkMessage> Messages { get; set; } = new();

    /// <summary>
    /// 用户id，一个代表你的最终用户的唯一标识符 (字符串最大长度为32)
    /// </summary>
    [JsonProperty("uid")]
    public string? UserId { get; set; }

    /// <summary>
    /// 内容审核的场景策略 (可取值： default ；默认值为 default)
    /// </summary>
    [JsonIgnore]
    public SparkOptionAuditing? Auditing { get; set; }

    [JsonProperty("auditing")]
    internal string? AuditingRaw => Auditing?.Value?.ToString();

    /// <summary>
    /// 需要使用的领域 (可取值： general ；默认值为 general)
    /// </summary>
    [JsonIgnore]
    public SparkOptionDomain? Domain { get; set; }

    [JsonProperty("domain")]
    internal string? DomainRaw => Domain?.Value?.ToString();

    [JsonIgnore]
    private decimal? _randomThreshold;

    /// <summary>
    /// 核采样阈值 (最小值是0，最大值是1，默认值为0)
    /// </summary>
    [JsonProperty("random_threshold")]
    public decimal? RandomThreshold {
        get => _randomThreshold;
        set {
            if (value is < 0.0m or > 1.0m) {
                throw new SparkException(new SparkError() {
                    Code = "FST_ERR_PARAMETER",
                    Message = "Bad Request",
                    Details = "RandomThreshold should be within range of 0-1"
                });
            }
            
            _randomThreshold = value;
        }
    }

    private int? _maxTokens;

    /// <summary>
    /// 回答的tokens的最大长度 (最小值是1，最大值是4096，默认值为1024)
    /// </summary>
    [JsonProperty("max_tokens")]
    public int? MaxTokens {
        get => _maxTokens;
        set {
            if (value is < 1 or > 4096) {
                throw new SparkException(new SparkError() {
                    Code = "FST_ERR_PARAMETER",
                    Message = "Bad Request",
                    Details = "MaxTokens should be within range of 1-4096"
                });
            }

            _maxTokens = value;
        }
    }

    /// <summary>
    /// 是否支持流式 (目前 SDK 不支持流式)
    /// </summary>
    [JsonProperty("stream")]
    public bool? Stream { get; set; }

    /// <summary>
    /// 向会话和提问数组中增加消息
    /// </summary>
    /// <param name="message">消息文本内容</param>
    /// <param name="role">角色</param>
    public void AddMessage(string message, SparkMessageRole role) {
        Messages.Add(new SparkMessage() {
            Content = message,
            Role = role
        });
    }

    /// <summary>
    /// 向会话和提问数组中增加消息
    /// </summary>
    /// <param name="message">消息</param>
    public void AddMessage(SparkMessage message) {
        Messages.Add(message);
    }

    /// <summary>
    /// 转换为 JSON
    /// </summary>
    /// <returns></returns>
    public override string ToString() {
        return JsonConvert.SerializeObject(this, new JsonSerializerSettings() {
            NullValueHandling = NullValueHandling.Ignore
        });
    }
}