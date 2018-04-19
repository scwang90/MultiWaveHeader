# 多重水波纹 - MultiWaveHeader

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%2012%2B%20-f0ad4e.svg)](https://android-arsenal.com/api?level=14)
[![Platform](https://img.shields.io/badge/Platform-Android-f0ad4e.svg)](https://www.android.com)
[![Author](https://img.shields.io/badge/Author-scwang90-11bbff.svg)](https://github.com/scwang90)

## [English](https://github.com/scwang90/MultiWaveHeader/blob/master/README.md) | 中文

MultiWaveHeader 是一个可以高度定制每个波形的Android水波控件。

## 功能特点

 - 支持调节进度.
 - 支持调节速度.
 - 支持设置方向（上下）.
 - 支持设置水波的数量（无上限）.
 - 支持精确定义水波的参数（偏移、拉伸、原始速度）.
 - 支持设置颜色渐变和改变渐变方向.

## 演示
[Download APK-Demo](art/app-debug.apk)

### 实战

![](art/gif_index_preview.gif)


## 控制台

### 方向

|顶部|底部|
|:---:|:---:|
![](art/gif_console_1.gif)|![](art/gif_console_2.gif)|

|一对|单一|
|:---:|:---:|
![](art/gif_console_3.gif)|![](art/gif_console_4.gif)|

## Property

#### you can config basic attribute of waves.

###### java
```java
    MultiWaveHeader waveHeader = findViewById(R.id.waveHeader);

    waveHeader.setStartColor(R.color.colorPrimary);
    waveHeader.setCloseColor(R.color.colorPrimaryDark);
    waveHeader.setColorAlpha(.5f);

    waveHeader.setWaveHeight(50);
    waveHeader.setGradientAngle(360);
    waveHeader.setProgress(.8f);
    waveHeader.setVelocity(1f);
    waveHeader.setScaleY(-1f);

    waveHeader.setWaves("PairWave");
```

###### xml
```xml
    <com.scwang.wave.MultiWaveHeader
        android:id="@+id/waveHeader"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:scaleY="-1"
        app:mwhVelocity="1"
        app:mwhProgress="1"
        app:mwhGradientAngle="45"
        app:mwhWaveHeight="50dp"
        app:mwhColorAlpha="0.45"
        app:mwhStartColor="@color/colorPrimaryDark"
        app:mwhCloseColor="@color/colorPrimaryLight"
        app:mwhWaves="MultiWave">
```


## Custom

#### You can customize each wave with precision.

###### java
```java
    MultiWaveHeader waveHeader = findViewById(R.id.waveHeader);


    String[] waves = new String[]{
        "70,25,1.4,1.4,-26",//wave-1:offsetX(dp),offsetY(dp),scaleX,scaleY,velocity(dp/s)
        "100,5,1.4,1.2,15",
        "420,0,1.15,1,-10",//wave-3:offsetX(dp),offsetY(dp),scaleX,scaleY,velocity(dp/s)
        "520,10,1.7,1.5,20",
        "220,0,1,1,-15",
    };
    waveHeader.setWaves(TextUtils.join(" ", Arrays.asList(waves)));// custom
    waveHeader.setWaves("PairWave");// default two waves
    waveHeader.setWaves("MultiWave");// default five waves

```

###### xml
```xml
    <com.scwang.wave.MultiWaveHeader
        android:id="@+id/waveHeader"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        app:mwhWaves="PairWave"
        app:mwhWaves="MultiWave"
        app:mwhWaves="
            70,25,1.4,1.4,-26
            100,5,1.4,1.2,15
            420,0,1.15,1,-10
            520,10,1.7,1.5,20
            220,0,1,1,-15">
```

License
-------

    Copyright 2017 scwang90

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
