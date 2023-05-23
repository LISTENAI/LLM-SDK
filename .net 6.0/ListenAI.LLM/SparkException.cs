using ListenAI.LLM.Models.Response;

namespace ListenAI.LLM; 

public class SparkException : Exception {
    public SparkException(SparkError error) : base(error.Message) {
        
    }
    
    public SparkError Error { get; set; }
}