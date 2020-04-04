package com.example.beginapplication


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class TestActivity : AppCompatActivity() {
    val TAG = "TAG"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observable = Observable.just("one", 2 , true, 1.3)
        val observableOfNumber = Observable.range(0, 1000).zipWith(Observable.interval(10, TimeUnit.MILLISECONDS), BiFunction<Int, Any, Int>{t1, t2 -> t1 })
        val list = mutableListOf(1, 2, 3, 4, 5, "t", 7, 8, 9, 0)

        val dispos = Observable.create<Int> {
            for (i in list) {
                it.onNext(i as Int)
            }
            it.onError(Throwable())
            it.onComplete()
        }.scan { t1, t2 -> t1 + t2 }.subscribe({
            Log.i(TAG, it.toString())
        }, {
            Log.i(TAG, it.localizedMessage)
        })








        val subject = PublishSubject.create<Any>()
        observableOfNumber.subscribe(subject)
//
//
//        observable.subscribe(observer)
//
//        Handler().postDelayed({
//            Log.e(TAG, "Observer one start")
//                subject.subscribe{
//                    Log.i(TAG, it.toString())
//                }
//            }, 40)
//
//        Handler().postDelayed({
//            Log.e(TAG, "Observer two start")
//                subject.subscribe {
//                    Log.i(TAG, it.toString())
//                }
//        }, 80)






    }


}



