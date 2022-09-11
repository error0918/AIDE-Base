AIDE BASE (20220801-1)
 이 프로젝트는 나온지 8년도 더 된 Android Lolipop (Api21)을 기반으로 한 AIDE에서 Material M2 또는 더 최신에 가까운 앱을 만들기 위한 베이스이다. (2022/08/01 ~)


수정 내역

APP NAME
- 앱 이름에 띄어 쓰기 또는 특문수문자 포함 시, 생성 이후 프로젝트 폴더 이름, /app/src/main/res/values/strings.xml app_name 수정

/README.md
- 설명용 파일
- 이후 다른 프로젝트에서 사용 X

/app/libs
- 폴더 생성

/app/Build.Gradle
- minSdkVersion 21
- compileSdkVersion 29로 변경
- buildToolsVersion "21.1.0" 삭제
- testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner" 추가
- dependencies: 필요한 부분 복사해서 사용

/app/src/main/AndroidManifest.xml
- <uses-permission android:name="android.permission.VIBRATE" /> 추가
- 런처 엑티비티를 ".MainActivity"에서 ".SplashLayout"으로 변경
- <activity android:name=".MainActivity"/> 추가

/app/src/main/java/com/taeyeon/앱이름/SplashActivity.java
- 파일 생성
- 내용 작성
- 추후 사용시 패키지 정보 수정

/app/src/main/java/com/taeyeon/core/이하 코어 파일들
 - 이후 복사해서 사용
 - 이후 파일들의 "import com.taeyeon.aide_base.*;"를 "import 패키지명.*;"로 변경
 
 /app/src/main/res/drawable
- 폴더 생성
 
/app/src/main/res/menu
- 폴더 생성

app/src/main/res/values/colors.xml
- 파일 생성
- 형식 입력
 
 /app/src/main/res/values/strings.xml
- "hello_world" 삭제
- <string name="error_happen">에러 발생 (%1$s)</string>
<string name="copy_toast_message">%1$s(이)가 복사되었습니다.</string>
<string name="copy_toast_message_with_label">%2$s copied to %1$s</string> 추가
 
/app/src/main/res/values/styles.xml
- themes.xml로 이름 변경
 
/app/src/main/res/values/themes.xml
- name을 "Theme.App으로 변경"
- parent를 "Theme.AppCompat.DayNight.NoActionBar"로 변경
 
/app/src/main/res/values-en
- 폴더 생성

/app/src/main/res/values-en/strings.xml
- 파일 생성
- 형식 입력
- app_name 생성 (앱 마다 다르게 수정)
- <string name="error_happen">Error Happen (%1$s)</string>
<string name="copy_toast_message">%1$s copied</string>
<string name="copy_toast_message_with_label">%2$s copied to %1$s</string> 추가
  
/app/src/main/res/values-ko
- 폴더 생성
 
 /app/src/main/res/values-ko/strings.xml
- 파일 생성
- 형식 입력
- app_name 생성 (앱 마다 다르게 수정)
- <string name="error_happen">에러 발생 (%1$s)</string> 
<string name="copy_toast_message">%1$s(이)가 복사되었습니다.</string>
<string name="copy_toast_message_with_label">%2$s copied to %1$s</string> 추가
 
/app/src/main/res/values-night
- 폴더 생성

/app/src/main/res/values-night/colors.xml
- 파일 생성
- 형식 입력
 
/app/src/main/res/values-v21
- 폴더 및 파일 삭제