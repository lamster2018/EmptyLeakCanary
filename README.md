# EmptyLeakCanary

We can implemation LeakCanary 1.* in different version,

Because squareup support release version lib like this 

```
releaseImplementation 'com.squareup.leakcanary:leakcanary-android-no-op:1.5.4'
```

however they nou support it in 2.* .

so i try to give android developers same way to use LeakCanary 2.* when we compile release apk in same code.

```
releaseImplementation 'com.lahm.library:leakcanary-android-no-op:0.0.2'
```
