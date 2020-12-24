package ru.lemaitre.databasetest.presentation.student_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.Database
import ru.lemaitre.databasetest.data.db.model.Entities

class StudentDetailViewModel : ViewModel() {
    val repository = UniversityRepository()

    private val studentsMutableLiveData = MutableLiveData<List<Entities.Student>>()
    val students: LiveData<List<Entities.Student>>
        get() = studentsMutableLiveData

    fun getStudentsById(id: Long) {
        viewModelScope.launch {
            try {
                val list = repository.getAllStudent().filter { it.id == id }
                Log.d("TAG", list.toString())
                studentsMutableLiveData.postValue(list)
            } catch (t: Throwable) {
                Log.d("TAG", "fail load student by group id", t)
                studentsMutableLiveData.postValue(emptyList())
            }
        }
    }

    private val gradeMLD = MutableLiveData<List<Entities.Grades>>()
    val gradeLD: LiveData<List<Entities.Grades>>
        get() = gradeMLD

    fun getGradeByIdStudent(id: Long) {
        viewModelScope.launch {
            try {
                gradeMLD.postValue(repository.getAllGrades().filter { it.studentsId == id })
            } catch (t: Throwable) {
                Log.d("TAG", "fail load grade", t)
                gradeMLD.postValue(emptyList())
            }
        }
    }

    private val desciplineMLD = MutableLiveData<List<Entities.Descipline>>()
    val descipline: LiveData<List<Entities.Descipline>>
        get() = desciplineMLD

    fun getDesciplineById(idDescipline: Long) {
        viewModelScope.launch {
            try {
                desciplineMLD.postValue(repository.getAllDescipline().filter { it.id == idDescipline })
            } catch (t: Throwable) {
                Log.d("TAG", "throwable getDisciplineById", t)
            }
        }
    }

    private val descplineNameMLD = MutableLiveData<Map<String, String>>()
    val desciplineName: LiveData<Map<String, String>>
        get() = descplineNameMLD

    fun getNameGrades(idStudent: Long){
        viewModelScope.launch {
            try {
                
                val listGrade = repository.getAllGrades().filter { it.studentsId == idStudent }
                val s = repository.getAllDescipline()
                val list = mutableListOf<Entities.Descipline>()
                for(i in listGrade){
                    for(l in s){
                        if(i.disciplineId.equals(l.id)){
                            list.add(l)
                        }
                    }
                }
                val result = list.zip(listGrade)
                val myMap = mutableMapOf<String, String>()
                for(i in result){
                    myMap[i.first.name] = i.second.name
                }
                descplineNameMLD.postValue(myMap)
            }catch (t:Throwable){
                descplineNameMLD.postValue(emptyMap())
            }
        }
    }

}