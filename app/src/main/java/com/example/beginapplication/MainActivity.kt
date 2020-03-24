package com.example.beginapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
private val disposeBag = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: List<String> = listOf("one", "two", "three", "four", "five")

        val dispose = Observable.just(list)
            .flatMapIterable { it }
            .scan { t1: String?, t2: String? -> "$t1, $t2"}
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                text.text = it
            }, {

            })


//        val dispose = Observable.just("Алексей", "Владимир", "Грегорий", "Дмитрий", "Евгений")
//            .scan { t1: String, t2: String -> "$t1 $t2" }
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                println(it)
//            },{
//
//            })
//
//
//        disposeBag.addAll(dispose)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }
}
