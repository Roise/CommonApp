package com.tistory.deque.kotlinmvvmsample.advise.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.deque.kotlinmvvmsample.advise.base.BaseKotlinViewModel
import com.tistory.deque.kotlinmvvmsample.advise.model.DataModel
import com.tistory.deque.kotlinmvvmsample.advise.model.enum.KakaoSearchSortEnum
import com.tistory.deque.kotlinmvvmsample.advise.model.response.ImageSearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val model: DataModel) : BaseKotlinViewModel() {

    private val TAG = "MainViewModel"

    private val _imageSearchResponseLiveData = MutableLiveData<ImageSearchResponse>()
    val imageSearchResponseLiveData:LiveData<ImageSearchResponse>
        get() = _imageSearchResponseLiveData

    fun getImageSearch(query: String, page:Int, size:Int) {
        addDisposable(model.getData(query, KakaoSearchSortEnum.Accuracy, page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    if (documents.size > 0) {
                        Log.d(TAG, "documents : $documents")
                        _imageSearchResponseLiveData.postValue(this)
                    }
                    Log.d(TAG, "meta : $meta")
                }
            }, {

                val data = ImageSearchResponse.Document("test", "test", "test", 300, 300, "display", "test"
                ,"test")
                val meta = ImageSearchResponse.Meta(100, 20, false)
                var list = mutableListOf<ImageSearchResponse.Document>()
                list.add(data)
                val resource =
                    ImageSearchResponse(
                        list as ArrayList<ImageSearchResponse.Document>,
                        meta
                    )
                _imageSearchResponseLiveData.postValue(resource)
                Log.d(TAG, "response error, message : ${it.message}")
            }))
    }
}