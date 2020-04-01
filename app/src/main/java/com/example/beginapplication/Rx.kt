package com.example.beginapplication


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableObserver
import java.util.concurrent.TimeUnit


class Rx : AppCompatActivity() {
    val TAG = "TAG"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observable = Observable.just("one", 2 , true, 1.3)



//        val observer = object : DisposableObserver<Int>() {
//            override fun onComplete() {
//                Log.i(TAG, "Observable is complete")
//            }
//
//            override fun onNext(t: Int) {
//               Log.i(TAG, "$t")
//            }
//
//            override fun onError(e: Throwable) {
//                Log.e(TAG, e.localizedMessage)
//            }
//        }
//
//
//        observable.subscribe(observer)

//        Handler().postDelayed({
//            Log.e(TAG, "Observer is dispose")
//                observer.dispose()
//            }, 10000)


        val iObserver = object : DisposableObserver<Any>() {
            override fun onComplete() {
                TODO("Not yet implemented")
            }

            override fun onNext(t: Any) {
                Log.e(TAG, t.toString())
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

        }


        observable.subscribe(iObserver)

    }


}



