namespace ListenAI.LLM; 

public class Constants {
    internal static class UrlFactory {
        private const string Prefix = "https://api.listenai.com";
        internal const string Completions = $"{Prefix}/v1/spark/completions";
    }
}

public class SparkMessageRole {
    internal SparkMessageRole(string value) { Value = value; }
    public string Value { get; private set; }
    
    public static SparkMessageRole User => new("user");
    public static SparkMessageRole Assistant => new("assistant");

    public override string ToString() {
        return Value;
    }
}

public class SparkOptionAuditing {
    internal SparkOptionAuditing(string value) { Value = value; }
    public string Value { get; private set; }
    
    public static SparkOptionAuditing Default => new("default");

    public override string ToString() {
        return Value;
    }
}

public class SparkOptionDomain {
    internal SparkOptionDomain(string value) { Value = value; }
    public string Value { get; private set; }
    
    public static SparkOptionDomain General => new("general");

    public override string ToString() {
        return Value;
    }
}