package com.example.beginapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       dataSource()
           .subscribeOn(Schedulers.newThread())
           .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    text.text = it.toString()
                },
                {

                }, {
                    Log.e("tag", "finish")
                })

        }

    }

    private fun dataSource(): Observable<Int> = Observable.create { subscriber ->
        for (i in 1..100) {
            Thread.sleep(100)
            subscriber.onNext(i)

        }

    }

