package dev.titto.userlistapp.presentation.mvp.userlist

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface ScheduleProvider {
    fun io(): Scheduler
    fun main(): Scheduler
}

class ScheduleProviderImpl() : ScheduleProvider {
    override fun io() = Schedulers.io()
    override fun main() = AndroidSchedulers.mainThread()
}

class TestScheduleProvider() : ScheduleProvider {
    override fun io() = Schedulers.trampoline()
    override fun main() = Schedulers.trampoline()
}
