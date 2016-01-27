package fishing.sunshine.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by sunshine on 12/20/15.
 */
public class FishFan extends SunshineEntity {
    private String fishFanId;
    private String username;
    private double longitude;
    private double latitude;

    public String getFishFanId() {
        return fishFanId;
    }

    public void setFishFanId(String fishFanId) {
        this.fishFanId = fishFanId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
