//package websocket;
//
//import com.fasterxml.jackson.databind.JsonNode;
//
//public class testServer {
//
//    private static WSServer wsServer;
//
//    public static void main(String[] args) {
//        WebSocketSetting setting = new WebSocketSetting();
//        final testServer testServer = new testServer();
//
//        setting.setHost("10.8.0.42");
//        setting.setPort(8024);
//        wsServer = WSServer.createWSServer(setting);
//
//        wsServer.start();
//
//        while (true) {
//            wsServer.addMessageHandler(msg -> processData((String) msg));
//        }
//    }
//
//    public static void processData(String msg) {
//
//        JsonNode node = Json.toJsonNode(msg);
//        if (node.get("type").asText().equals("info")) {
//            System.out.println("心跳" + msg);
//            return;
//        }
//        System.out.println(node);
//
//        if (node.get("plc").asText().equals("L1F04") && node.get("tag").asText().equals(".L1F04_1Al1_1A4460RB_1A4460RB.SB.S1071_Occ")) {
//            String res1 = "{\"type\":\"dataChange\",\"id\":\"" + node.get("id").asText() + "\",\"tag\":\"L1F04.L1F04_1Al1_1A4460RB_1A4460RB.SB.S1071_Occ\",\"ts\":\"2022-07- 22T11:37:56.262+08:00\",\"value\":\"" + RandomUtils.nextInt() + "\",\"quality\":192,\"source\":\"EMOS\",\"userRights\":0}";
//            new Timer().setPeriodic(3000, () -> wsServer.broadcast(res1));
//        } else if (node.get("plc").asText().equals("L1F03") && node.get("tag").asText().equals(".L1F03_1Al1_1A4460RB_1A4460RB.SB.S1071_Occ")) {
//            String res2 = "{\"type\":\"dataChange\",\"id\":\"" + node.get("id").asText() + "\",\"tag\":\"L1F03.L1F03_1Al1_1A4460RB_1A4460RB.SB.S1071_Occ\",\"ts\":\"2022-07- 22T11:37:56.262+08:00\",\"value\":\"" + RandomUtils.nextInt() + "\",\"quality\":192,\"source\":\"EMOS\",\"userRights\":0}";
//            new Timer().setPeriodic(6000, () -> wsServer.broadcast(res2));
//        }
//
//    }
//
//
//
//
//
//}
