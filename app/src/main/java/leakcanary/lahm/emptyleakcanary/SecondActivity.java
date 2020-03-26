package leakcanary.lahm.emptyleakcanary;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lahm.emptyleakcanary.R;

/**
 * Created by Lahm.Long on 2020/03/26
 * desc :
 */
public class SecondActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // 100% memory leak
        LeakSingleton.getInstance().setContext(this);
    }
}
