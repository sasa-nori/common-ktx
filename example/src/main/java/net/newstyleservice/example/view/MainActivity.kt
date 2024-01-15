package net.newstyleservice.example.view

import android.os.Build.VERSION
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import net.newstyleservice.example.Preferences
import net.newstyleservice.example.R
import net.newstyleservice.example.R.id
import net.newstyleservice.example.databinding.ActivityMainBinding
import net.newstyleservice.example.di.ViewModelFactory
import ss_n.common_ktx.Permissions
import ss_n.common_ktx.SoundPool
import ss_n.common_ktx.extension.hasNotificationPermission
import ss_n.common_ktx.extension.loadSoundPool
import ss_n.common_ktx.extension.setNotificationChannel
import ss_n.common_ktx.extension.setOnSingleClickListener
import ss_n.common_ktx.observer.EventObserver
import javax.inject.Inject

class MainActivity : InjectActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mainViewModel by viewModels<MainViewModel> { viewModelFactory }

    private val binding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    private var soundPathList: MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnSingleClickListener { view ->
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
        showNotificationRuntimePermission()
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

    private fun showNotificationRuntimePermission() {
        if (VERSION.SDK_INT >= 33) {
            if (hasNotificationPermission()) return
            // No authorization alert without creating a channel.
            // チャンネル作成をしないと権限アラートが出ない
            setNotificationChannel("testApp", "testNotification")
            requestPermissions(Permissions.NOTIFICATION.permissions, 1000)
        }
    }
}
