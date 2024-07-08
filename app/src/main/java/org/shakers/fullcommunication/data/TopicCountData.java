package org.shakers.fullcommunication.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;

public class TopicCountData {
    ArrayList<String> topicList = new ArrayList<>();


    public TopicCountData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        final int TOPIC_COUNT_MAX = 21;
        for (int i = 1; i <= TOPIC_COUNT_MAX; i++) {
            String key = String.valueOf(i);
            String topic = preferences.getString(key, null);
            if (topic != null) {
                topicList.add(topic);
            }
        }
        Collections.shuffle(topicList);
    }

    public ArrayList<String> getTopicList() {
        return topicList;
    }
}
