scanpay-demo-android
====================

A sample application to scan credit cards using ScanPay API

To give it a try:

1. Clone this repo.
2. Go to https://scanpay.it and sign up for a account and create a new app
3. Download the SDK and Copy the folder libs/ into the Project
4. Get the token of your app and in ScanPayTestActivity.java set the value of "ENTER_YOUR_TOKEN_HERE"

Try and enjoy !


Requirements for card scanning
------------------------------

* Android SDK version 8 (Android 2.2) or later 
* ARMv7 processor

Instruction
-----------

1. Copy the content of ScanPay libs folder into your project libs folder
2. If using Eclipse, right click on your project name and click properties and add jar libScanpay.jar
3. Add these permissions to your AndroidManifest.xml

  ```xml
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
  <uses-permission android:name="android.permission.INTERNET" />
  <uses-permission android:name="android.permission.CAMERA" />
  <uses-permission android:name="android.permission.VIBRATE" />
  <uses-feature android:name="android.hardware.camera" android:required="false" />
  <uses-feature android:name="android.hardware.camera.autofocus" android:required="false" />
  <uses-feature android:name="android.hardware.camera.flash" android:required="false" />
  ```

5. Declare the activity used by ScanPay in your AndroidManifest.xml

  ```xml
  <!-- ScanPay Activities -->
  <activity
      android:name="scanpay.it.ScanPayActivity"
      android:configChanges="orientation|keyboardHidden"
      android:screenOrientation="portrait"
      android:theme="@android:style/Theme.Black.NoTitleBar" >
  </activity>
  <activity
      android:name="scanpay.it.SPValidationActivity"
      android:screenOrientation="portrait"
      android:theme="@android:style/Theme.Black.NoTitleBar"
      android:windowSoftInputMode="stateHidden" >
  </activity>
  ```

Sample code
-----------

Start scanning using
  ```java
  Intent scanActivity = new Intent(this, ScanPayActivity.class);
  scanActivity.putExtra(ScanPay.EXTRA_TOKEN, "ENTER_YOUR_TOKEN_HERE");
  // If you want use your own credit card UI form
  scanActivity.putExtra(ScanPay.EXTRA_USE_CUSTOM_CONFIRMATION_VIEW, true);
  startActivityForResult(scanActivity, YOUR_RESULT_SCANPAY_ACTIVITY);
  ```

To get the scan result, you must implement onActivityResult as follows

  ```java
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RESULT_SCANPAY_ACTIVITY && resultCode == ScanPay.RESULT_SCAN_SUCCESS)
    {
      SPCreditCard creditCard = (SPCreditCard) data.getParcelableExtra(ScanPay.EXTRA_CREDITCARD);
      Toast.makeText(this, creditCard.number + " " + creditCard.month + "/" + creditCard.year + " " + creditCard.cvv, Toast.LENGTH_LONG).show();
    }
    else if (requestCode == RESULT_SCANPAY_ACTIVITY && resultCode == ScanPay.RESULT_SCAN_CANCEL)
    {
      Toast.makeText(this, "Scan cancel", Toast.LENGTH_LONG).show();
    }
  }
  ```

Before you build in release mode, make sure to adjust your proguard configuration by adding the following to proguard.cnf

  ```
  -keep class scanpay.it.**
  -keepclassmembers class scanpay.it.** {
    *;
  }
  ```
