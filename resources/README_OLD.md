<img src="https://github.com/S1lentHurr1cane/TUC-App/blob/master/resources/ring-co_TUC-01.svg">

# TUC Companion App
Repository for the TUC companion app.

## Notes for final build
* Do NOT use a `NoActionBar` theme. I want a consistent toolbar througout the app, which is best accomplished with the default toolbar.
* Make sure to have `OnPreferenceChange()` return __true__ if I want the preference change to actually persist.
  * This is unnecessary unless I need to do something specific at the exact time a preference is changed.
* Can I use a `Snackbar` to inform the user that they're not connected to the right WiFi network when selecting Controller from the Connection Screen?
* `androidx.preference.PreferenceScreen` pretty much sucks, so don't rely on it more than is necessary.
* For the `ListPreference` component, both the `entries` and `entryValues` attributes are necessary. These may be defined in res/values/arrays.xml.
* Use JoystickView from [this](https://github.com/controlwear/virtual-joystick-android) repo by adding `implementation 'io.github.controlwear:virtualjoystick:1.10.1'` in build.gradle (Module: app)'s "dependencies" section.
* Use `android:dependency=""` to make some preferences depend on the setting of another.
* In general, do __NOT__ mess with the theme, you're only making things worse for yourself.
* Using floating action buttons might look/act nicer for some of the toggles; just need to use `OnTouchListener()` instead of `OnClickListener()`. There are also arrow drawables in resources that would be perfect for the d-pad.
* Preference objects DO update SharedPreferences, but they're saved in a default file that I don't know the name of. Use `PreferenceManager.getDefaultSharedPreferences(***context here***)` to access these. Context should either be `this` or `getApplicationContext()`.
* To have custom colors for different button states, add a drawable `.xml` file like `bg_button.xml`
  * Helpful colors from [here](https://www.materialui.co/socialcolors)
* Material design resources can be found [here](https://material.io/).
  * [Icons](https://material.io/tools/icons/?style=baseline)

## Notes for ecomatController code
* Joystick (+ display?) need to override input from app.
  * There's certainly a logical way to achieve this
* Angle/strength from virtual joystick will need to be converted to usable values.
* Should data be sent back to the app from the TUC (speed, etc.)?
* Should the app kick the user back to the selection or login screen if the TUC disconnects?
