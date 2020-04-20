package leakcanary.lahm.emptyleakcanary;

import android.util.Log;

import leakcanary.LeakCanary;

/**
 * Project Name:EmptyLeakCanary
 * Package Name:leakcanary.lahm.emptyleakcanary
 * Created by lahm on 2020-03-01 17:38 .
 * <p>
 * More code see my github https://github.com/lamster2018
 */
public class TestApplication extends NormalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        setLeakCanary2Config();
        Log.d("leak", "onCreate:TestApplication ");
    }

    private void setLeakCanary2Config() {
        LeakCanary.Config config = LeakCanary.getConfig()
                .newBuilder()
                .retainedVisibleThreshold(1)
                .maxStoredHeapDumps(1)
                .onHeapAnalyzedListener(new TestLeakMemUploader())
                .build();
        LeakCanary.setConfig(config);
    }
}
