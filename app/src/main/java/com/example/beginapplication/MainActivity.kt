package com.example.beginapplication


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val disposeBag = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // TODO Возвращать стрим в метод
        val bool1 = Flowable.just(false, false, false, false, true, true)
        val bool2 = Flowable.just(true, false, false, true, false, false)

        val num = Flowable.just(1, 23, 4, 5, 6, 7, 8)


        bool1.withLatestFrom(bool2, BiFunction<Boolean, Boolean, Boolean> { t1, t2 -> t1 && t2 })
            .doAfterNext { voidFun() }
            .onErrorReturnItem(false)
            .switchMap { item ->
                return@switchMap if (item) {
                    num.takeUntil{0 == it % 2}
                        .subscribeOn(Schedulers.computation())
                        .map { "${1.0 / it}" }

                } else {Flowable.just("")} }
            .observeOn(Schedulers.newThread())
            .doOnNext {
                println("Ура ${Thread.currentThread().name}")
            }
            .subscribeOn(Schedulers.io())
            .subscribe ({
                println(it)
            },{
                println(it.printStackTrace())
            })

    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }

    fun voidFun() {}
}





