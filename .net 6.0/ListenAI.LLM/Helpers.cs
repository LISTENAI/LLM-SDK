using System.Net;
using System.Net.Http.Headers;
using System.Text;
using ListenAI.LLM.Models.Response;

namespace ListenAI.LLM; 

internal class Helpers {
    public static HttpReturn HttpOperation(string url, HttpMethod method, string body = "", string contentType = "", string token = "") {
        using var hc = new HttpClient(new HttpClientHandler() {
            AllowAutoRedirect = false
        });
        hc.DefaultRequestHeaders.TryAddWithoutValidation("User-agent", $"ListenAI LLM SDK/1.0.0 ({Environment.OSVersion})");
        if (!string.IsNullOrWhiteSpace(token)) {
            hc.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", token);
        }
        
        var req = new HttpRequestMessage(method, url);
        if (req.Method == HttpMethod.Post || req.Method == HttpMethod.Put || req.Method == HttpMethod.Patch) {
            req.Content = new StringContent(body, Encoding.UTF8, contentType);
        }

        try {
            var resp = hc.SendAsync(req).Result;
            return new HttpReturn() {
                Code = resp.StatusCode,
                Content = resp.Content.ReadAsStringAsync().Result
            };
        }
        catch (Exception ex) {
            return new HttpReturn() {
                Code = HttpStatusCode.PaymentRequired,
                Content = ex.Message
            };
        }
    }
}