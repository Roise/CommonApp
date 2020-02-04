package com.roi.common.application.network

import com.roi.common.application.model.BookDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {
    companion object {
        var BOOK_INFORMATION_URL = "http://azine.kr/m/_api/apiEbook.php?code=107&page="
        var BOOK_INFORMATION_ADD_URL = "&cnt=12&gid=0&t="

        const val BOOK_INFORMATION_BASE_URL = "http://azine.kr/m/_api/"
    }

    @GET("apiEbook.php")
    fun getBookList(@Query("code") code: Int,
                    @Query("page") page: Int,
                    @Query("cnt") cnt: Int): Single<BookDto>
}

