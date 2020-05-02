package net.newstyleservice.example

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.newstyleservice.common_ktx.HttpClient
import net.newstyleservice.common_ktx.extension.createRetrofitService
import net.newstyleservice.common_ktx.extension.setOnSingleClickListener

class MainActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        URL.createRetrofitService(
            service = ApiService::class.java,
            client = HttpClient.createCustomClient()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnSingleClickListener { view ->
            view?.let {
                val pref = Preferences(this)
                pref.tapCount++
                Snackbar.make(it, "Tapped count is ${pref.tapCount}", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

                requestApi()
            }
        }
    }

    private fun requestApi() = lifecycleScope.launch {
        val result = withContext(Dispatchers.IO) {
            apiService.getShikure()
        }
        result?.let {
            it.forEach { shikure ->
                Log.d("response content", shikure.content)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val URL = "https://mhf.newstyleservice.net"
    }
}
