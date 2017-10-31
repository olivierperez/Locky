# Locky Android

Locky Android helps you to protect your Android applications with a pad screen.

- At the first use, the user will choose a Pin as long as he want.
- All the following starts will be locked by the pad screen, the user's data will be protected.

<a href="https://github.com/olivierperez/Locky/raw/master/art/screenshot/Locky_choose_code.png">
    <img src="https://github.com/olivierperez/Locky/raw/master/art/screenshot/Locky_choose_code.png" width="200"/>
</a>
<a href="https://github.com/olivierperez/Locky/raw/master/art/screenshot/Locky_unlock_pad.png">
    <img src="https://github.com/olivierperez/Locky/raw/master/art/screenshot/Locky_unlock_pad.png" width="200"/>
</a>

## Usage

### Configure Locky

```java
import fr.o80.locky.api.LockyConf;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Configure Locky
        LockyConf.init(new LockyConf(this)
                .withBackground(R.color.colorPrimary)
                .withTextColor(android.R.color.white)
                .withTitle(R.string.app_name)
                .withTexts(R.string.choose_code, R.string.confirm_code));
    }

}
```

### Check if user is locked

```java
import fr.o80.locky.api.LockyConf;

public class MainActivity extends AppCompatActivity {

    // To handle pad result
    private static final int REQUEST_CODE_LOCK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        
        // Check if user is locked
        if (LockyConf.getInstance().isLocked()) {
        
            // User is locked, or didn't choose a pin => Show pad screen
            Intent intent = LockyConf.getInstance().lockActivity(this);
            startActivityForResult(intent, REQUEST_CODE_LOCK);
            
        } else {
            // User is unlocked
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Handle pad result
        if (requestCode == REQUEST_CODE_LOCK) {
            switch (resultCode) {
                case LockyConf.RESULT_CANCELED:
                    finish();
                    break;
                case LockyConf.RESULT_CHECK:
                    Toast.makeText(this, "Authentication checked", Toast.LENGTH_SHORT).show();
                    break;
                case LockyConf.RESULT_ENROLLED:
                    Toast.makeText(this, "Enrolment finished", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}
```

## Download

Locky Android is available on [JitPack](https://jitpack.io/#olivierperez/locky),
add the JitPack repository in your top level `build.gradle`:
```gradle
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}
```
and the dependency in the `build.gradle` of the module: [![](https://jitpack.io/v/olivierperez/locky.svg)](https://jitpack.io/#olivierperez/locky)

```gradle
dependencies {
    compile 'com.github.olivierperez:locky:1.2:release@aar'
}
```

## License

    Copyright 2016 Olivier Perez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
