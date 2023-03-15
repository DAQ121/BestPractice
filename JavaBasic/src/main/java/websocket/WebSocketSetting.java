package websocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Data
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebSocketSetting {

    // 供 client 使用的请求地址
    private String url;

    // 用于创建 websocket 的 server
    private String host;
    private Integer port;

    private Map<String, String> httpHeaders;

    // 用于解析数据的JS脚本
    private String script;

    private int connectTimeout = 0;

}
