# 聆思大模型 SDK

## 当前支持语言
- .Net 6.0 (C#)
- Java 17

## 安装方法

### .Net 6.0 (C#)

打开 Package Manager Console，然后运行以下指令

```powershell
PM> NuGet\Install-Package ListenAI.LLM
```

### Java 17

添加以下内容至 pom.xml 文件
```xml
    <dependencies>
        <dependency>
            <groupId>com.listenai</groupId>
            <artifactId>llm</artifactId>
            <version>0.1.0</version>
        </dependency>
    </dependencies>
```
然后执行以下指令以安装依赖
```shell
mvn install
```

## 支持的请求参数

| 名称 | 类型 | 说明 | 是否必填 | 备注 |
| --- | --- | --- | --- | --- |
| uid | string | 用户id，一个代表你的最终用户的唯一标识符 | 否 | 字符串最大长度为32 |
| auditing | string | 内容审核的场景策略 | 否 | 可取值： default ；默认值为 default |
| domain | string | 需要使用的领域 | 否 | 可取值： general ；默认值为 general |
| random_threshold | float | 核采样阈值 | 否 | 最小值是0，最大值是1，默认值为0 |
| max_tokens | int | 回答的tokens的最大长度 | 否 | 最小值是1，最大值是4096，默认值为1024 |
| messages | Array\<message\> | 会话和提问 | 是 | 需要自行拼接对话历史信息，数组最后是最新的用户提问 |
| message.role | string | 角色 | 是 | 可取值： user，assistant ；其中user表示用户的提问，assistant表示AI的回复 ; 若当前message为messages数组的最后一个元素，role的值需为字符串'user' |
| message.content | string | 文本内容 | 是 | 该角色的对话内容 |

## 快速开始

### .Net 6.0 (C#)

以下是一个快速开始的 Demo 
```C#
using ListenAI.LLM;
using ListenAI.LLM.Models;

namespace Tester
{
    internal class Program
    {
        static void Main(string[] args)
        {
            new TesterProgram().Run();
        }
    }

    internal class TesterProgram {
        public void Run() {
            var spark = new SparkInstance(new SparkOptions() {
                Token = "(聆思大模型的API Key)"
            });

            
            var prompt = "你好呀";
            var reqOptions = new CompletionOptions();
            reqOptions.AddMessage(prompt, SparkMessageRole.User);
            Console.WriteLine($"===> {prompt}");
            try {
                var resp = spark.Conversations(reqOptions);
                ConsoleOutput($"<=== {resp.Choices[0].Message.Content}");
                reqOptions.AddMessage(resp.Choices[0].Message);
            } catch (SparkException spex) {
                ConsoleOutput($"<=== Exception: ({spex.Error.Code}) {spex.Error.Details}");
            }
        }
    }
}
```

如API Key正确，网络通讯正常。开发者会得到类似这样的输出：
```
===> 你好呀
<=== 你好！很高兴和你交流。请问有什么问题我可以帮您解答吗？
```

### Java 17
以下是一个快速开始的 Demo 
```java
import com.listenai.llm.SparkException;
import com.listenai.llm.SparkInstance;
import com.listenai.llm.models.request.CompletionOptions;
import com.listenai.llm.models.request.SparkMessage;
import com.listenai.llm.models.request.SparkOptions;
import com.listenai.llm.models.response.CompletionResponse;

public class Main {
    public static void main(String[] args) {
        var opt = new SparkOptions();
        opt.setToken("(聆思大模型的API Key)");
        var spark = new SparkInstance(opt);

        var prompt = "你好呀";
        var reqOptions = new CompletionOptions();
        reqOptions.addMessage(prompt, "user");
        System.out.println("===> " + prompt);
        try {
            var resp = spark.Conversations(reqOptions);
            var respMsg = resp.getChoices().get(0).getMessage();
            System.out.println("<=== " + respMsg.getContent());
            reqOptions.addMessage(respMsg);
        } catch (SparkException e) {
            System.out.println("<=== Exception: (" + e.getError().getCode() + ") " + e.getError().getDetails());
        }
    }
}
```
如API Key正确，网络通讯正常。开发者会得到类似这样的输出：
```
===> 你好呀
<=== 你好！很高兴和你交流。请问有什么问题我可以帮您解答吗？
```