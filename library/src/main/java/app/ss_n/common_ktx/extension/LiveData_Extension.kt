package app.ss_n.common_ktx.extension

import androidx.lifecycle.LiveData
import app.ss_n.common_ktx.observer.TestObserver

/**
 * Used in unit tests
 */
fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}
