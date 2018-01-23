package com.example.panagiotis.skylarapiex.model.realm;

import io.realm.RealmObject;

/**
 * Created by Panagiotis on 23/01/2018.
 */

public class CaschData extends RealmObject {
    String name;
    String url;
    String uuid;

    public CaschData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
