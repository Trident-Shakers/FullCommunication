package org.shakers.fullcommunication.data;

import java.util.HashMap;
import java.util.Random;

public class TopicCountMockData {

    String[] topicList = {"スポーツ", "ゲーム", "音楽", "料理", "旅行", "アニメ", "漫画", "SNS", "ファッション", "映画", "アート", "ダンス", "読書", "動物", "自然", "ギャンブル", "物作り", "勉強", "歌う", "乗り物", "写真"};
    private String topicTimeListString = "";

    public TopicCountMockData() {
        StringBuilder topicTimeListStringBuilder = new StringBuilder();
        // for文の反復回数を20までのうちからランダムに決定
        Random random = new Random();
        int loopCount = random.nextInt(20);
        HashMap<String, Integer> topicCountMap = new HashMap<>();
        for (int i = 0; i < loopCount; i++) {
            topicCountMap.put(topicList[i], generateRandomMilliSeconds());
        }

        // topicCountMapの内容を文字列に変換
        for (String key : topicCountMap.keySet()) {
            topicTimeListStringBuilder.append(key).append("-").append(topicCountMap.get(key)).append(",");
        }
        topicTimeListString = topicTimeListStringBuilder.toString();
        topicTimeListString = topicTimeListString.substring(0, topicTimeListString.length() - 1);
    }

    public int generateRandomMilliSeconds() {
        Random random = new Random();
        //ミリ秒で20秒から100秒の間でランダムな値を生成
        return random.nextInt(100000) + 20000;
    }

    public String getTopicTimeListString() {
        return topicTimeListString;
    }

}
