# MultiWaveHeader

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![JCenter](https://img.shields.io/badge/%20Jcenter%20-1.0.0-5bc0de.svg)](https://bintray.com/scwang90/maven/MultiWaveHeader/_latestVersion)
[![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%209%2B%20-f0ad4e.svg)](https://android-arsenal.com/api?level=9)
[![Platform](https://img.shields.io/badge/Platform-Android-f0ad4e.svg)](https://www.android.com)
[![Author](https://img.shields.io/badge/Author-scwang90-11bbff.svg)](https://github.com/scwang90)


## English | [中文](https://github.com/scwang90/MultiWaveHeader/blob/master/README.md)

MultiWaveHeader is an android wave view that can be highly customized for each wave.

## Features

 - Support adjust progress.
 - Support adjust velocity.
 - Support adjust direction.
 - Support adjust waves number.
 - Support precise customization waves.
 - Support color gradient and adjust angle.

## Demo

[Download APK-Demo](https://github.com/scwang90/MultiWaveHeader/blob/master/art/app-debug.apk?raw=true)

![](art/png_apk_rqcode.png)

### Practical

![](art/gif_index_preview.gif)


## Console

### direction

|top|bottom|
|:---:|:---:|
![](art/gif_console_1.gif)|![](art/gif_console_2.gif)|

|pair|single|
|:---:|:---:|
![](art/gif_console_3.gif)|![](art/gif_console_4.gif)|

## Usage
#### 1.Add a gradle dependency.
```
    implementation 'com.scwang.wave:MultiWaveHeader:1.0.0'
    //androidx
    implementation 'com.scwang.wave:MultiWaveHeader:1.0.0-andx'
```

#### 2.Add MultiWaveHeader in the layout xml.
```xml
    <com.scwang.wave.MultiWaveHeader
        android:id="@+id/waveHeader"
        android:layout_width="match_parent"
        android:layout_height="200dp"/>
```

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

    waveHeader.start();
    waveHeader.stop();
    waveHeader.isRunning();

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
        app:mwhRunning="true"
        app:mwhGradientAngle="45"
        app:mwhWaveHeight="50dp"
        app:mwhColorAlpha="0.45"
        app:mwhStartColor="@color/colorPrimaryDark"
        app:mwhCloseColor="@color/colorPrimaryLight"
        app:mwhWaves="MultiWave"/>
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
            220,0,1,1,-15"/>
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
