package com.example.beginapplication


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
private val disposeBag = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = Observable.just("one", "two", "three", "four", "five")

//        val disposes = Observable.just(list)
//            .flatMapIterable { it }
//            .scan { t1: String?, t2: String? -> "$t1, $t2"}
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({})

       RxTextView.textChanges(editText)
           .debounce(300, TimeUnit.MILLISECONDS)
           .skip(3)
           .subscribe { Log.e("TAG", it.toString()) }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }
}
