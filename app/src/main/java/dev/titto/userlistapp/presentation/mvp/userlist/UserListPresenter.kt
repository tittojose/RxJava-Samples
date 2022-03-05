package dev.titto.userlistapp.presentation.mvp.userlist

import dev.titto.userlistapp.domain.repository.UserRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class UserListPresenter constructor(
    val view: UserListView,
    val repo: UserRepository,
    private val schedulers: ScheduleProvider
) {
    private val disposable = CompositeDisposable()

    fun fetchUsers() {
        repo.getUserList()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
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