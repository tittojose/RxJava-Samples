package dev.titto.userlistapp.presentation.mvp.userlist

import android.util.Log
import dev.titto.userlistapp.domain.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

class UserListPresenter constructor(
    val view: UserListView,
    val repo: UserRepository
) {
    private val disposable = CompositeDisposable()
    var apiHitCount = 0
    var apiHitLimit = 5
    fun fetchUsers() {
        repo.getUserList()
            .repeatWhen {

                Log.d(TAG, "fetchUsers: repeatWhen: apiHitCount= $apiHitCount")
                it.delay(5, TimeUnit.SECONDS);
            }
            .filter {
                apiHitCount++
                Log.d(TAG, "fetchUsers: filter: apiHitCount= $apiHitCount")
                apiHitCount == apiHitLimit
            }
            .takeUntil{
                Log.d(TAG, "fetchUsers: filter: takeUntil= $apiHitCount")
                apiHitCount == apiHitLimit
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "fetchUsers: onComplete")
                view.showUserList(it.userList)
            }, {
                Log.d(TAG, "fetchUsers: onError")
            }).addTo(disposable)
    }

    fun onViewDestroyed() {
        disposable.clear()
    }
}