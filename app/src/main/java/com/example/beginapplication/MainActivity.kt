package com.example.beginapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.schedulers.Schedulers


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       dataSsource()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({list ->
                list.forEach {
                    text.text = it.toString()
                }
            },{
                Log.e("tag", "$it")
            })
    }


    private fun dataSource(): Observable<Int> {
        return Observable.create { subscriber ->
            for (i in 1..10000000) {

                subscriber.onNext(i)
            }

            subscriber.onComplete()
        }
    }

    private fun dataSsource(): Single<List<Int>> {
        return Single.create { subscriber ->
           val list = listOf(1,2,3,4,5,6,7,8)
            subscriber.onSuccess(list)
        }
    }
}
