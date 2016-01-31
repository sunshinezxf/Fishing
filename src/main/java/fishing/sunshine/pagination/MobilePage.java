package fishing.sunshine.pagination;

import java.util.List;

/**
 * Created by sunshine on 1/31/16.
 */
public class MobilePage<T> {
    private long total;
    private List<T> data;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
