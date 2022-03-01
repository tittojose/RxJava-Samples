package dev.titto.userlistapp.presentation.mvp.userlist

import dev.titto.userlistapp.domain.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class UserListPresenter constructor(
    val view: UserListView,
    val repo: UserRepository
) {
    private val disposable = CompositeDisposable()

    fun fetchUsers() {
        repo.getUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showUserList(it.userList)
            }, {
                view.showErrorMessage("Error loading data")
            }).addTo(disposable)
    }

    fun onViewDestroyed() {
        disposable.clear()
    }
}