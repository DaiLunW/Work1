import java.io.*;
import java.util.regex.*;

public class Extractstring {
    public static void main(String[] args) {
        try {
            // 读取源代码文件
            BufferedReader reader = new BufferedReader(new FileReader("source_code.txt"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            // 使用正则表达式匹配双引号之间的文本
            Pattern pattern = Pattern.compile("\"(\\\\.|[^\"])*\"");
            Matcher matcher = pattern.matcher(sb.toString());
            while (matcher.find()) {
                String str = matcher.group();
                // 去掉双引号和转义字符
                str = str.substring(1, str.length() - 1).replaceAll("\\\\(.)", "$1");
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}