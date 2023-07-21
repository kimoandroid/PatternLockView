# PatternLockView
An easy-to-use, customizable, Material Design ready Pattern Lock view for Android.

[![](https://jitpack.io/v/kimoandroid/PatternLockView.svg)](https://jitpack.io/#kimoandroid/PatternLockView)
[![](https://jitci.com/gh/kimoandroid/PatternLockView/svg)](https://jitci.com/gh/kimoandroid/PatternLockView)
[![Build](https://github.com/kimoandroid/PatternLockView/actions/workflows/android.yml/badge.svg)](https://github.com/kimoandroid/PatternLockView/actions/workflows/android.yml)
![GitHub release (with filter)](https://img.shields.io/github/v/release/kimoandroid/PatternLockView)
![Repo Size](https://img.shields.io/github/repo-size/kimoandroid/PatternLockView)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/839fd92cb64e410a977f0b4835a535f3)](https://app.codacy.com/gh/kimoandroid/PatternLockView/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Lines Of Code](https://tokei.rs/b1/github/kimoandroid/PatternLockView?category=code)](https://github.com/kimoandroid/PatternLockView)

[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)
[![Discord](https://img.shields.io/discord/954020097381502976.svg?label=&logo=discord&logoColor=ffffff&color=7389D8&labelColor=6A7EC2)](https://discord.gg/ptz6VByDbv)

![PatternLockView](https://github.com/kimoandroid/PatternLockView/assets/69405523/248b0c5e-6c50-4530-b250-f060095ffe44)


This library allows you to implement pattern locking mechanism in your app **easily and quickly**. It is very easy to use and there are **plenty of customization options** available to change the functionality and look-and-feel of this view to match your needs.

It also **supports RxJava3 view bindings**, so if you are a fan of reactive programming (just like me), you can get a stream of updates as the user draws the pattern.

## Preview
![PatternLockView](https://github.com/kimoandroid/PatternLockView/assets/69405523/1b004c21-be05-481b-80c5-eb4a61939f1f)
![PatternLockView](https://github.com/kimoandroid/PatternLockView/assets/69405523/1748da32-a654-4544-a85c-dffe09c53792)


## Example App
[https://github.com/kimoandroid/PatternLockView/tree/master/app](https://github.com/kimoandroid/PatternLockView/tree/master/app)


# Usage

## Gradle
> Add this line to root `build.gradle` at allprojects block code:
```gradle
allprojects {
  repositories {
   //...
   maven { url 'https://jitpack.io' }
  }
 }
 ```

> then add this line into your `build.gradle` app level.
```gradle
dependencies {
    implementation 'com.github.kimoandroid:patternlockview:1.0'

    // Optional, for RxJava3 adapter
    implementation 'com.github.kimoandroid:patternlockview_reactive:1.0'
}
```

<br>

### Step 1
Add this widget into your `xml` activity file
```xml
    <co.encept.patternlockview.PatternLockView
        android:id="@+id/pattern_lock_view"
        android:layout_width="280dp"
        android:layout_height="280dp"/>
```

This is enough to get the view rendered in your layout. But you would certainly want to add a callback listener to listen to pattern changes.

### Step 2

Reference the view in code and add a listener to it.

```java
mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
mPatternLockView.addPatternLockListener(mPatternLockViewListener);
```

Implement the listener interface as follows,

```java
private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(), "Pattern drawing started");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(), "Pattern progress: " +
                    PatternLockUtils.patternToString(mPatternLockView, progressPattern));
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Log.d(getClass().getName(), "Pattern complete: " +
                    PatternLockUtils.patternToString(mPatternLockView, pattern));
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Pattern has been cleared");
        }
    };
```

And that's it! Your PatternLockView is ready to rock. You might also want to remove the listeners when not needed,         `removePatternLockListener(mPatternLockViewListener);`

<br><br>

### Step 3 (Optional: ReactiveX Interface)

For the RxJava fanboys, this library supports RxJava3 view bindings. You can subscribe to this view to get a stream of pattern change updates.

```java
RxPatternLockView.patternChanges(mPatternLockView)
                .subscribe(new Consumer<PatternLockCompoundEvent>() {
                    @Override
                    public void accept(PatternLockCompoundEvent event) throws Exception {
                        if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_STARTED) {
                            Log.d(getClass().getName(), "Pattern drawing started");
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_PROGRESS) {
                            Log.d(getClass().getName(), "Pattern progress: " +
                                    PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_COMPLETE) {
                            Log.d(getClass().getName(), "Pattern complete: " +
                                    PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_CLEARED) {
                            Log.d(getClass().getName(), "Pattern has been cleared");
                        }
                    }
                });
```

If you are not interested in getting the compound event, you should subscribe to `patternComplete()` and/or `patternProgress()` for the specific updates. Have a detailed look [here](https://github.com/kimoandroid/PatternLockView/blob/master/patternlockview_reactive/src/main/java/co/encept/rxpatternlockview/RxPatternLockView.java).

<br>

# Customization
There are several customization options available which you can use to completely change the look-and-feel and functionality of this view to match your needs.

<br>

### XML (Quick and Easy)
You can add various attributes to the PatternLockView from your XML layout.

```xml
  app:dotCount="3"                                        // Change the no.of dots in a row (or column)
  app:dotNormalSize="12dp"                                // Change the size of the dots in normal state
  app:dotSelectedSize="24dp"                              // Change the size of the dots in selected state
  app:pathWidth="4dp"                                     // Change the width of the path
  app:aspectRatioEnabled="true"                           // Set if the view should respect custom aspect ratio
  app:aspectRatio="square"                                // Set between "square", "width_bias", "height_bias"
  app:normalStateColor="@color/white"                     // Set the color of the pattern view in normal state
  app:correctStateColor="@color/primary"                  // Set the color of the pattern view in correct state
  app:wrongStateColor="@color/pomegranate"                // Set the color of the pattern view in error state     
  app:dotAnimationDuration="200"                          // Change the duration of the animating dots
  app:pathEndAnimationDuration="100"                      // Change the duration of the path end animaiton
```

<br>

### JAVA (Programatically)
You can also programatically change the properties of the view, thereby having more control over it.

```java
mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);       // Set the current viee more 
mPatternLockView.setInStealthMode(true);                                     // Set the pattern in stealth mode (pattern drawing is hidden)
mPatternLockView.setTactileFeedbackEnabled(true);                            // Enables vibration feedback when the pattern is drawn
mPatternLockView.setInputEnabled(false);                                     // Disables any input from the pattern lock view completely

mPatternLockView.setDotCount(3);
mPatternLockView.setDotNormalSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_size));
mPatternLockView.setDotSelectedSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_selected_size));
mPatternLockView.setPathWidth((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_path_width));
mPatternLockView.setAspectRatioEnabled(true);
mPatternLockView.setAspectRatio(PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS); 
mPatternLockView.setNormalStateColor(ResourceUtils.getColor(this, R.color.white));
mPatternLockView.setCorrectStateColor(ResourceUtils.getColor(this, R.color.primary));
mPatternLockView.setWrongStateColor(ResourceUtils.getColor(this, R.color.pomegranate));
mPatternLockView.setDotAnimationDuration(150);
mPatternLockView.setPathEndAnimationDuration(100);

```

<br>

#### That's All don't forget to star the project & fork if you want to develop the library.

#### Powered by [Encept Ltd](https://encept.co).

#### * This Library Was Forked From: [https://github.com/aritraroy/PatternLockView](https://github.com/aritraroy/PatternLockView)

<br>

## Contributors
<a href="https://github.com/kimoandroid/PatternLockView/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=kimoandroid/PatternLockView" />
</a>
<br>

## Stargazers
[![Stargazers repo roster for @kimoandroid/PatternLockView](https://reporoster.com/stars/kimoandroid/PatternLockView)](https://github.com/kimoandroid/PatternLockView/stargazers)


## Forkers
[![Forkers repo roster for @kimoandroid/PatternLockView](https://reporoster.com/forks/kimoandroid/PatternLockView)](https://github.com/kimoandroid/PatternLockView/network/members)
