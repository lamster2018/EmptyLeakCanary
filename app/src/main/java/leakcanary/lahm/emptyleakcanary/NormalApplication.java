package leakcanary.lahm.emptyleakcanary;

import android.app.Application;
import android.util.Log;


/**
 * Project Name:EmptyLeakCanary
 * Package Name:leakcanary.lahm.emptyleakcanary
 * Created by lahm on 2020-03-01 17:38 .
 * <p>
 * More code see my github https://github.com/lamster2018
 */
public class NormalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("leak", "onCreate: NormalApplication");
    }

}
