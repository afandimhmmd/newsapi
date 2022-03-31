package com.afandimuhammad.newsapitutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.os.Build;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Locale;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class testMainActivity extends ViewTest{
    ActivityScenario<MainActivity> scenario;
    private String packageName = "com.afandimuhammad";
    private String targetDevice = "9";
    private int minSDK = 21;
    private String actName = "MainActivity";
    private String layoutName = "activity_main";
    private String backwardComp = "AppCompatActivity";

    @Before
    public void initTest(){
        scenario = ActivityScenario.launch(MainActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
    }

    @Test
    public void check_UI_1(){
        scenario.onActivity(activity -> {
           assertEquals("Application Name is Wrong", appName.toLowerCase(), getAppName(activity.getPackageName()));
        });
    }

    @Test
    public void check_UI_2(){
        scenario.onActivity(activity -> {
            String packName = packageName+"."+appName.toLowerCase();
            assertEquals("Package Name is Wrong",packName,activity.getPackageName());
        });
    }

    @Test
    public void check_UI_3(){
        scenario.onActivity(activity -> {
            Assert.assertEquals("Target Device is Wrong",targetDevice, Build.VERSION.RELEASE);
        });
    }

    @Test
    public void check_UI_4(){
        scenario.onActivity(activity -> {
            Assert.assertEquals("Minimum SDK Version is Wrong",minSDK,activity.getApplicationInfo().minSdkVersion);
        });
    }

    @Test
    public void check_UI_5(){
        scenario.onActivity(activity -> {
            Assert.assertEquals("Activity Name is Wrong", actName, activity.getClass().getSimpleName());
        });
    }

    @Test
    public void check_UI_6(){
        scenario.onActivity(activity -> {
            int resId = activity.getResources().getIdentifier(layoutName, "layout", activity.getPackageName());
            assertNotEquals("Layout Name is Wrong", 0, resId);
        });
    }

    @Test
    public void check_UI_7(){
        scenario.onActivity(activity -> {
            Assert.assertEquals("Activity Parent is Wrong", backwardComp, activity.getClass().getSuperclass().getSimpleName());
        });
    }

    @Test
    public void check_UI_8(){
        scenario.onActivity(activity -> {
            com.afandimuhammad.newsapitutorial.ResourceTest rsc = new com.afandimuhammad.newsapitutorial.ResourceTest(activity.getResources());
            rsc.testStringResource("app_name","newsapitutorial");
        });
    }

    @Test
    public void check_UI_9(){
        scenario.onActivity(activity -> {
            com.afandimuhammad.newsapitutorial.ResourceTest rsc = new com.afandimuhammad.newsapitutorial.ResourceTest(activity.getResources());
            rsc.testColorResource(activity,"purple_200","#BB86FC");
            rsc.testColorResource(activity,"purple_500","#6200EE");
            rsc.testColorResource(activity,"purple_700","#03A9F4");
            rsc.testColorResource(activity,"teal_200","#03DAC5");
            rsc.testColorResource(activity,"teal_700","#018786");
            rsc.testColorResource(activity,"black","#000000");
            rsc.testColorResource(activity,"white","#FFFFFF");
            }); //FF didepan dihapus agar tidak eror
    }

    @Test
    public void check_UI_10(){
        scenario.onActivity(activity -> {
            testViewExist("include","TabLayout",activity);

        });
    }

    /**@Test
    public void check_UI_11(){
        scenario.onActivity(activity -> {
            testViewExist("home","TabItem",activity);
        });
    }
    **/

    private String getAppName(String packageName) {
        String[] list = packageName.split("\\.");
        String res = list[list.length-1];
        return res;
    }


}
