package com.roi.common.application.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.roi.common.application.model.BookDto
import com.roi.common.application.network.BookRepository
import io.reactivex.disposables.CompositeDisposable

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: BookRepository
    private val disposable: CompositeDisposable

    private var _mBookListLiveData: MutableLiveData<BookDto>? = null
    val mBookListLiveData get() = _mBookListLiveData

    init {
        repository = BookRepository()
        repository.initializeApi()

        disposable = CompositeDisposable()
    }

    override fun onCleared() {
        super.onCleared()
        if(!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    fun addDisposable(disposable: CompositeDisposable) {
        disposable.add(disposable)
    }

    fun loadBookData(code:Int, page: Int, cnt: Int): MutableLiveData<BookDto>? {
        _mBookListLiveData = repository.getBookData(code, page, cnt)
        return _mBookListLiveData
    }
}