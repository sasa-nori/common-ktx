package ss_n.common_ktx.extension

import androidx.lifecycle.LiveData
import ss_n.common_ktx.test.TestObserver

/**
 * Used in unit tests
 */
fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}
