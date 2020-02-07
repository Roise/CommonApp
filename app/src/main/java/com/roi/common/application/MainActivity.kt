package com.roi.common.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.roi.common.application.viewModel.BookViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mBookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        initView()
    }

    fun initView() {

    }
}
