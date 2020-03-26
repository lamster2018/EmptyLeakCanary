package leakcanary.lahm.emptyleakcanary;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Lahm.Long on 2020/03/26
 * desc :
 */
public class LeakSingleton {
    private static Context mContext;

    private static volatile LeakSingleton singleton;

    private LeakSingleton() {

    }

    public static LeakSingleton getInstance() {
        if (singleton == null) {
            synchronized (LeakSingleton.class) {
                if (singleton == null) {
                    singleton = new LeakSingleton();
                }
            }
        }
        return singleton;
    }

    public void setContext(Context context) {
        mContext = context;
    }


}
