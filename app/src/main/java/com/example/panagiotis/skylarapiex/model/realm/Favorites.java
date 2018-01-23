package com.example.panagiotis.skylarapiex.model.realm;

import io.realm.RealmObject;

/**
 * Created by Panagiotis on 23/01/2018.
 */

public class Favorites extends RealmObject {
    String name;
    String imageUrl;
    String contentUrl;
    String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Favorites() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
