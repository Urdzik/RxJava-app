package com.example.beginapplication


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
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
        val bool1 = Observable.just(false, false, false, false, true, true)
        val bool2 = Observable.just(true, false, false, true, false, false)

        val num = Observable.just(2, 2, 2, 5, 6, 7, 8)


        bool1.withLatestFrom(bool2, BiFunction<Boolean, Boolean, Boolean> { t1, t2 -> t1 && t2 })
            .doAfterNext { voidFun() }
            .onErrorReturnItem(false)
            .switchMap { item ->
                return@switchMap if (item) {
                    Observable.create{ sub ->
                        num.takeUntil{0 == it % 2}.take(1)
                        .subscribeOn(Schedulers.computation())
                        .map { "${1.0 / it}" }
                            .subscribe({
                                Log.i("TAG", it)
                            },{
                                Log.e("TAG", it.localizedMessage)
                            })
                    }
                } else {Observable.just("")} }
            .observeOn(Schedulers.newThread())
            .doOnNext {
                Log.i("TAG", "Ура ${Thread.currentThread().name}")
            }
            .subscribeOn(Schedulers.io())
            .subscribe ({
                Log.i("TAG", it)
            },{
                Log.e("TAG", it.localizedMessage)
            })

    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }

    fun voidFun() {}
}





