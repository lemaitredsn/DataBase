package ru.lemaitre.databasetest.presentation.add.add_grade

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.Entities

class AddGradeViewModel : ViewModel() {
    private val repository = UniversityRepository()

    private val gradeMLD = MutableLiveData<List<Entities.Grades>>()
    val grades: LiveData<List<Entities.Grades>>
        get() = gradeMLD


    private val desciplineMLD = MutableLiveData<List<Entities.Descipline>>()
    val desciplines: LiveData<List<Entities.Descipline>>
        get() = desciplineMLD

    fun loadDescipline() {
        viewModelScope.launch {
            try {
                desciplineMLD.postValue(repository.getAllDescipline())
            } catch (t: Throwable) {
                desciplineMLD.postValue(emptyList())
                Log.d("TAG", "loadDescipline fail", t)
            }
        }
    }

    fun addGrade(idStudent: Long, idDescipline: Long, nameGrade: String) {
        val grade = Entities.Grades(
                name = nameGrade,
                studentsId = idStudent,
                disciplineId = idDescipline)
        viewModelScope.launch {
            try {
                repository.addGrade(listOf(grade))
            } catch (t: Throwable) {
                Log.d("TAG", "add grade failed", t)
            }
        }
    }


}