package com.netease.cloud.test;

import com.netease.cloud.client.StreamProxyClient;
import com.netease.cloud.util.json.JSONArray;
import com.netease.cloud.util.json.JSONObject;

public class StreamProxySample {

    private static String accessKey = "1a51246df23424c957819f0d97e5d5c";
    private static String secretKey = "41121a691234248113420f674efb00";

    public static void main(String[] args) throws Exception {

        String subscriptionName = "test201612151903.de342lt-wm3zq";
        String positionType = "EARLIEST";
        StreamProxyClient client = null;

        try {
            // get subscription position
            client = new StreamProxyClient(accessKey, secretKey);
            String ret = client.getSubscriptionPosition(positionType, subscriptionName);
            System.out.println(ret);

            // get subscription logs
            JSONObject retObject = new JSONObject(ret);
            String logsPosition = retObject.getJSONObject("result").getString("position");
            long limit = 10;
            String logs = client.getLogs(logsPosition, limit, subscriptionName);
            System.out.println(logs);

            // cal number of subscription logs
            JSONObject logsObject = new JSONObject(logs);
            JSONArray subscription_logs =
                    logsObject.getJSONObject("result").getJSONArray("subscription_logs");
            System.out.println(subscription_logs.length());
        } catch (Exception e) {
            System.out.println("Execute error " + e.getMessage());
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }

    }

}
