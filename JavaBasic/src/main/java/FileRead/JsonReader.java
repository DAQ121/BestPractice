package FileRead;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class JsonReader {
    public static void main(String[] args) {
        // read file
        read();
        // write file
        write();

    }

    /**
     * 遍历整个目录下的所有文件, 使用jackson处理
     */
    public static void read() {
        // 可是是绝对路径，也可以是相对路径，注意这个路径要有访问权限
        String directoryPath = "JavaBasic";
        ObjectMapper mapper = new ObjectMapper();

        // 遍历深度，一直到底
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath), Integer.MAX_VALUE)) {

            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath) && filePath.toString().endsWith(".json")) {
                    File jsonFile = filePath.toFile();
                    try {
                        Map<String, Object> jsonMap = mapper.readValue(jsonFile, Map.class);
                        System.out.println(jsonMap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write()  {
        String filePath = "JavaBasic/file.json";
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "John Doe");
        jsonMap.put("age", 35);
        jsonMap.put("address", "New York");
        try {
            // 格式化写入
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), jsonMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
