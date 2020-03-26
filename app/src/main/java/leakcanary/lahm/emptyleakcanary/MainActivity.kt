package leakcanary.lahm.emptyleakcanary

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lahm.emptyleakcanary.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.go).setOnClickListener(View.OnClickListener {
            var i: Intent = Intent(this, SecondActivity::class.java)
            startActivity(i)
        })
    }
}
