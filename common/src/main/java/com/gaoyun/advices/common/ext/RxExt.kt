package com.gaoyun.advices.common.ext

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T: Any> Single<T>.ioToMain(): Single<T> = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())