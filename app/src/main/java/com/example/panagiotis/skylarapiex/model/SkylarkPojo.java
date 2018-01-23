package com.example.panagiotis.skylarapiex.model;

/**
 * Created by Panagiotis on 22/01/2018.
 */

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkylarkPojo {

    @SerializedName("objects")
    @Expose
    private List<PojoObject> objects = null;

    public List<PojoObject> getObjects() {
        return objects;
    }

    public void setObjects(List<PojoObject> objects) {
        this.objects = objects;
    }

}
