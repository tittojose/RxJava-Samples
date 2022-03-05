package dev.titto.userlistapp.presentation.rxsearch

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object SearchViewRxHelper {
    fun getTextChangeObservable(editText: EditText): Observable<String> {
        val publishSubject = PublishSubject.create<String>()

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun afterTextChanged(p0: Editable?) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                publishSubject.onNext(p0?.toString() ?: "")
            }
        })
        return publishSubject
    }
}