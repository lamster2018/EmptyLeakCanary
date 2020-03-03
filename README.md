# EmptyLeakCanary


## How to use

```
releaseImplementation 'com.lahm.library:leakcanary-android-no-op:0.0.2'
```

# Documention
## English

LeakCanary 2.* no need init in our application, but sometimes we have to set config in our code,maybe like this(java code)

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

We usually need use leak canary only in debug ,not in release.

if we set config and complie release apk,ooops,we will see compile error.

'leakcanary not found'.

Squareup provide us two different versions of LeakCanary 1.* and suggest us use it like

```
debugImplementation 'com.squareup.leakcanary:leakcanary-android:1.*'
releaseImplementation 'com.squareup.leakcanary:leakcanary-android-no-op:1.*'
```

however, squareup not provide LeakCanary 2.* release version to us, 

you will find why at this link
https://github.com/square/leakcanary/issues/979

if your team do not have enough resource to test debug version，you can try  this lib.

so,I clone the source code and delete all unnessary code,

it will have zero chance init in your application ,only support you a same name class ,avoid compile error.

## 中文
LeakCanary 2.* 现在不用在application初始化了，是无感的，但是我们有时候会特别设置它
比如做一些上传操作之类的
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
但是我们只需要在debug版本使用LeakCanary，release版本实际上是不需要的，

如果恰巧你需要对它进行设置，而且又只有一套代码，

那你可能就会遇到跟我一样的release编译问题，找不到leakcanary，shark 类。

以前Squareup是提供了两套代码

```
debugImplementation 'com.squareup.leakcanary:leakcanary-android:1.*'
releaseImplementation 'com.squareup.leakcanary:leakcanary-android-no-op:1.*'
```

核心原理就是，release版提供一个同包名的类，里面是空实现。至于为什么不再提供no-op
https://github.com/square/leakcanary/issues/979

如果你们也是没有更多的测试资源专门测debug包，必须用一套代码的话，试试我这个。

我这边参考了一下，clone了源代码，删除了不必要的代码，保证完全不会init，完全只提供同包名类，解决包名找不到的编译错误。

当然，这个库只提供最基础的配置设置。如果你还需要特别多的配置，那再说吧。


