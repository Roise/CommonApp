package com.roi.common.application.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.roi.common.application.model.BookDto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookRepository() {
    private val TAG = "BookRepository"
    fun initializeApi() {
        bookApi = RetrofitService.createService(BookApi::class.java)
    }
    //<<

    companion object {
        private var bookApi: BookApi? = null
    }

    fun getBookData(code: Int, page: Int, cnt: Int): MutableLiveData<BookDto> {
        val list = MutableLiveData<BookDto>()
        bookApi?.getBookList(code, page, cnt)?.subscribeOn(Schedulers.io())!!
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    list.value = it
                }, {
                    Log.d(TAG, "response error, message : ${it.message}")
                })

//        bookApi?.getBookList(code, page, cnt)?.enqueue(object : Callback<BookDto> {
//            override fun onResponse(call: Call<BookDto>, response: Response<BookDto>) {
//                if(response.isSuccessful) {
//                    list.value = response.body()
//                }
//            }
//            override fun onFailure(call: Call<BookDto>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
        return list
    }
}