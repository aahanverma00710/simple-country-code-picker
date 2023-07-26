# Simple-country-code-picker
User-Friendly Interface: Our Country Code Picker provides a clean and intuitive user interface, making it easy for users to interact and select their desired country code effortlessly
## Sample 
![](https://github.com/aahanverma00710/simple-country-code-picker/blob/main/art/Country%20Code.gif)
## How to import into Project

### Step 1. Add the JitPack repository to your build file
```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
### Step 2. Add the dependency
```groovy
	dependencies {
	        implementation 'com.github.aahanverma00710:simple-country-code-picker:Tag'
	}
```
Or Maven:

```xml
<dependency>
	    <groupId>com.github.aahanverma00710</groupId>
	    <artifactId>simple-country-code-picker</artifactId>
	    <version>Tag</version>
	</dependency>
```
## Usage

#### Declare activity in manifest 
```xml
   <activity android:name="com.avcoding.simplecpp.ui.SimpleCountryCodePickerActivity"/>
```

```kotlin
   val intent = Intent(this@MainActivity, SimpleCountryCodePickerActivity::class.java)
                countryCodePicker.launch(intent)
   private val countryCodePicker =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    val countryCodeData =
                        result.data?.extras?.parcelable(SimpleCountryCodePickerActivity.COUNTRY_CODE_ARGS) as CountryCodeData?
                    countryCodeData?.let {
                        // Handle the result here
                    }
                }
            }
        }
```


