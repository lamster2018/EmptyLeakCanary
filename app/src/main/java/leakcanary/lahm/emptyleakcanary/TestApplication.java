package leakcanary.lahm.emptyleakcanary;

import android.app.Application;

import leakcanary.LeakCanary;

/**
 * Project Name:EmptyLeakCanary
 * Package Name:leakcanary.lahm.emptyleakcanary
 * Created by lahm on 2020-03-01 17:38 .
 * <p>
 * More code see my github https://github.com/lamster2018
 */
public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setLeakCanary2Config();
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
