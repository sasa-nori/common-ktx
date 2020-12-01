package ss_n.common_ktx.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class TestObserver<T> : Observer<T> {

    val observedValues = mutableListOf<T?>()

    override fun onChanged(value: T?) {
        observedValues.add(value)
    }

    fun removeObserver(liveData: LiveData<T>) {
        liveData.removeObserver(this)
    }
}
