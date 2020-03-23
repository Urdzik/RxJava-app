package com.example.beginapplication


import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
private val disposeBag = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result1 = Observable.just("1", "2", "3", "4", "5")
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.newThread())
            .subscribe({
                println(it)
            },{
                println(it)
            })

        val result2 = Observable.just("1", "2", "3", "4", "5")
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.newThread())
            .subscribe({
                println(it)
            },{
                println(it)
            })
        disposeBag.addAll(result1, result2)

        Handler().postDelayed({
            println("Disposable")
            disposeBag.dispose()
        }, 2000)
    }
}
