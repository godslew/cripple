package com.godslew.core.android.extensions

import io.reactivex.*
import io.reactivex.disposables.Disposable
import timber.log.Timber

fun <T> Observable<T>.bindTo(onNext: (T) -> Unit): Disposable = subscribe(onNext) { Timber.e(it) }
fun <T> Observable<T>.execute(): Disposable = subscribe({}, { Timber.e(it) })

fun Completable.bindTo(onComplete: () -> Unit): Disposable = subscribe(onComplete) { Timber.e(it) }
fun Completable.execute(): Disposable = subscribe({}, { Timber.e(it) })

fun <T> Single<T>.bindTo(onSuccess: (T) -> Unit): Disposable = subscribe(onSuccess) { Timber.e(it) }
fun <T> Single<T>.execute(): Disposable = subscribe({}, { Timber.e(it) })

fun <T> Maybe<T>.bindTo(onSuccess: (T) -> Unit): Disposable = subscribe(onSuccess) { Timber.e(it) }
fun <T> Maybe<T>.execute(): Disposable = subscribe({}, { Timber.e(it) })

fun <T> Flowable<T>.bindTo(onNext: (T) -> Unit): Disposable = subscribe(onNext) { Timber.e(it) }
