# EmptyLeakCanary

We can implemation LeakCanary 1.* in different version,

Because squareup support release version lib like this 

```
releaseImplementation 'com.squareup.leakcanary:leakcanary-android-no-op:1.5.4'
```

however they are not support it in 2.* anymore.

so i try to give android developers same way to use LeakCanary 2.* when we compile release apk in same code.

```
releaseImplementation 'com.lahm.library:leakcanary-android-no-op:0.0.2'
```

以前LeakCanary 1.* 版本可以一套代码打包 debug 和 release的包，
因为squareup提供了com.squareup.leakcanary:leakcanary-android-no-op:1.5.4
这个库的核心思想就是一个提供一个同类名空实现的类。

现在LeakCanary2.* 不在提供release版本了，而且debug也不用在application里进行init操作
这可能造成如下情况
1. 我们需要设置leakCanary 参数，于是不得不在类里import包；
2. 但是代码只有一套，打包时需要屏蔽代码，操作麻烦；

这个库只提供最基本的LeakCanary.Config 设置项，可以视为不完美root。
可以参考

```java
    private void setLeakCanary2Config() {
        LeakCanary.Config config = LeakCanary.getConfig()
                .newBuilder()
                .retainedVisibleThreshold(1)
                .maxStoredHeapDumps(1)
                .onHeapAnalyzedListener(new TestLeakMemUploader())
                .build();
        LeakCanary.setConfig(config);
    }
```


