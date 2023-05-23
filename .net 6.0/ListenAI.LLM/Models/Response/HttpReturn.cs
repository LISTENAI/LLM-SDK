using System.Net;

namespace ListenAI.LLM.Models.Response; 

internal class HttpReturn {
    public HttpStatusCode Code { get; set; }
    
    public string Content { get; set; }
}