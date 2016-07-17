package com.liteng.dev.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by liteng on 16/7/16.
 */
@DatabaseTable(tableName = "DataCache")
public class DataCache {

    @DatabaseField(canBeNull = false, id = true)
    private String key;
    @DatabaseField(canBeNull = false)
    private String content;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "DataCache{" +
                "key='" + key + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
