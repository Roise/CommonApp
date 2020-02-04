package com.roi.common.application.network

import com.roi.common.application.network.BookApi.Companion.BOOK_INFORMATION_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object {
        private val retrofit = Retrofit.Builder()
                .baseUrl(BOOK_INFORMATION_BASE_URL)
                // 네트워크 요청 로그를 표시해 줍니다.
                .client(provideOkHttpClient(provideLoggingInterceptor()))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        // 네트뭐크 통신에 사용할 클라이언트 객체를 생성합니다.
        private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
            val b = OkHttpClient.Builder()
            // 이 클라이언트를 통해 오고 가는 네트워크 요청/응답을 로그로 표시하도록 합니다.
            b.addInterceptor(interceptor)
            return b.build()
        }

        // 네트워크 요청/응답을 로그에 표시하는 Interceptor 객체를 생성합니다.
        private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            return interceptor
        }

        fun <S> createService(serviceClass: Class<S>?): S {
            return retrofit.create(serviceClass)
        }
    }
}