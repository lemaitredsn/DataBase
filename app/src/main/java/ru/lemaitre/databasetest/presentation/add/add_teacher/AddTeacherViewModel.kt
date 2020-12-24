package ru.lemaitre.databasetest.presentation.add.add_teacher

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.Entities

class AddTeacherViewModel : ViewModel() {
    private val repository = UniversityRepository()

    fun addTeacher(
            id: Long,
            kaphedraId: Long,
            name: String,
            age: Int
    ) {
        val teacher = Entities.Teachers(
                id = id,
                kaphedraId = kaphedraId,
                name = name,
                age = age
        )
        viewModelScope.launch {
            try {
                repository.addTeacher(listOf(teacher))
            } catch (t: Throwable) {
                Log.d("TAG", "add teacher fail", t)
            }
        }
    }

}