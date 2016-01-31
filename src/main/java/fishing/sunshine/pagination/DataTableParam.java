package fishing.sunshine.pagination;

import java.util.Map;

/**
 * Created by sunshine on 12/7/15.
 */
public class DataTableParam {
    private int iDisplayStart;
    private int iDisplayLength;
    private String sEcho;
    private Map params;

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public Map getParams() {
        return params;
    }

    public int getPageNum() {
        int num = 0;
        if (getiDisplayStart() != 0) {
            num = getiDisplayStart() / getiDisplayLength();
            return num;
        }
        return num;
    }
}
