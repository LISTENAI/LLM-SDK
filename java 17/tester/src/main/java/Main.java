import com.listenai.llm.SparkException;
import com.listenai.llm.SparkInstance;
import com.listenai.llm.models.request.CompletionOptions;
import com.listenai.llm.models.request.SparkOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        var opt = new SparkOptions();
        opt.setToken("36d8ebc4-9a7e-4479-831a-a2dc0c282b3e");
        var spark = new SparkInstance(opt);

        var reqOptions = new CompletionOptions();
        var scanner = new Scanner(System.in);
        while (true) {
            System.out.print("===> ");
            var prompt = scanner.nextLine();
            if (prompt.equals("[clear]")) {
                reqOptions = new CompletionOptions();
                ConsoleOutput("=== New conversation!");
                continue;
            }

            reqOptions.addMessage(prompt, "user");
            ConsoleOutput("===> Requesting... " + prompt);

            try {
                var resp = spark.Conversations(reqOptions);
                var respMsg = resp.getChoices().get(0).getMessage();
                ConsoleOutput("<=== " + respMsg.getContent());
                reqOptions.addMessage(respMsg);
            } catch (SparkException e) {
                ConsoleOutput("<=== Exception: (" + e.getError().getCode() + ") " + e.getError().getDetails());
            }
        }
    }

    private static void ConsoleOutput(String str) {
        var dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println("[" + dateFormatGmt.format(new Date()) + "] " + str);
    }
}
