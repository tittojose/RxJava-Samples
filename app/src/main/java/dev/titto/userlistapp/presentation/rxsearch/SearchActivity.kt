package dev.titto.userlistapp.presentation.rxsearch

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.titto.userlistapp.data.remote.dto.UserDto
import dev.titto.userlistapp.data.repository.UserRepositoryImpl
import dev.titto.userlistapp.databinding.ActivitySearchBinding
import dev.titto.userlistapp.databinding.ActivityUserListBinding
import dev.titto.userlistapp.presentation.rxsearch.SearchViewRxHelper.getTextChangeObservable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class SearchActivity : AppCompatActivity() {

    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getTextChangeObservable(binding.searchEditText)
            .debounce(5, TimeUnit.SECONDS)
            .filter {
                if (it.isNullOrEmpty()) {
                    binding.searchEditText.setText("")
                    false
                } else {
                    true
                }
            }
            .distinctUntilChanged()
            .switchMap {
                Log.d("SearchActivity", "switchMap: $it")
                return@switchMap Observable.just("processed: $it")
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String?> {
                override fun onSubscribe(d: Disposable) {
                    Log.d("SearchActivity", "onSubscribe")
                }

                override fun onNext(t: String) {
                    Log.d("SearchActivity", "onNext $t")
                }

                override fun onError(e: Throwable) {
                    Log.d("SearchActivity", "onError $e")
                }

                override fun onComplete() {
                    Log.d("SearchActivity", "onComplete")
                }
            })
    }
}