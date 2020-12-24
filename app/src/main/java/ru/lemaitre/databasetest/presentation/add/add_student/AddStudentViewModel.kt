package ru.lemaitre.databasetest.presentation.add.add_student

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.Entities

class AddStudentViewModel: ViewModel() {

    private val repository = UniversityRepository()

    fun addStudent(
            id: Long,
            name: String,
            groupId: Long
    ) {
        val student = Entities.Student(
                id = id,
                name = name,
                groupStudentsId = groupId
        )
        viewModelScope.launch {
            try {
                    repository.addStudent(listOf(student))
            } catch (t: Throwable) {
                Log.d("TAG", "addStudent fail see stack", t)
            }

        }
    }

}