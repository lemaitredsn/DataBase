package ru.lemaitre.databasetest.presentation.group_students

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.Entities

class GroupStudentsViewModel: ViewModel() {
    private val repository = UniversityRepository()

    private val studentsMutableLiveData = MutableLiveData<List<Entities.Student>>()
    val students: LiveData<List<Entities.Student>>
        get() = studentsMutableLiveData

    fun getStudentsByIdGroup(groupId: Long){
        viewModelScope.launch {
            try{
                val list = repository.getAllStudent()
                val filteredList = list.filter { it.groupStudentsId == groupId }
                Log.d("TAG", filteredList.toString())
                studentsMutableLiveData.postValue(filteredList)
            }catch (t:Throwable){
                Log.d("TAG", "fail load student by group id", t)
                studentsMutableLiveData.postValue(emptyList())
            }
        }
    }

    private val groupMutableLiveData = MutableLiveData<List<Entities.GroupStudents>>()
    val group: LiveData<List<Entities.GroupStudents>>
        get() = groupMutableLiveData

    fun getGroupById(id:Long){
        viewModelScope.launch {
            try {
                val groupList = repository.getAllGroupStudents().filter { it.id == id }
                groupMutableLiveData.postValue(groupList)

            }catch (t:Throwable){
                groupMutableLiveData.postValue(emptyList())
            }
        }
    }

    fun deleteStudentAndHisGradeById(studentId: Long, groupId: Long){
        viewModelScope.launch {
            try {
                repository.deleteStudentAndGradeByIdStudent(studentId)
                getStudentsByIdGroup(groupId)
            }catch (t:Throwable){
                Log.d("TAG", "delete student by id - fail", t)
            }
        }
    }
}