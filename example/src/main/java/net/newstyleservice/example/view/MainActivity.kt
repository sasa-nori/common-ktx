package net.newstyleservice.example.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.coroutines.launch
import net.newstyleservice.example.Preferences
import net.newstyleservice.example.R
import net.newstyleservice.example.R.id
import net.newstyleservice.example.R.layout
import net.newstyleservice.todologinbonus.di.ViewModelFactory
import ss_n.common_ktx.SoundPool
import ss_n.common_ktx.extension.loadSoundPool
import ss_n.common_ktx.extension.setOnSingleClickListener
import ss_n.common_ktx.observer.EventObserver
import javax.inject.Inject

class MainActivity : InjectActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mainViewModel by viewModels<MainViewModel> { viewModelFactory }

    private var soundPathList: MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnSingleClickListener { view ->
            view?.let {
                val pref = Preferences(this)
                pref.tapCount++
                Snackbar.make(it, "Tapped count is ${pref.tapCount}", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

                mainViewModel.requestApi()

                SoundPool.play(soundPathList.first())
            }
        }

        // one shot event
        mainViewModel.getShikureList().observe(this, EventObserver { shikureList ->
            shikureList.forEach { shikure ->
                Log.d("response content", shikure.content)
            }
        })

        lifecycleScope.launch {
            soundPathList = loadSoundPool(mutableListOf(R.raw.one))
        }
    }

    override fun onDestroy() {
        SoundPool.release()
        super.onDestroy()
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
            id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
