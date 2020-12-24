package ru.lemaitre.databasetest.presentation.add.add_group_students

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.Entities

class AddGroupStudentsViewModel: ViewModel() {
    private val repository = UniversityRepository()

    private val groupStudentsMutableLiveData = MutableLiveData<List<Entities.GroupStudents>>()
    val groupStudentsLiveData: LiveData<List<Entities.GroupStudents>>
        get() = groupStudentsMutableLiveData

    fun addGroupStudent(
            id: Long,
            name:String,
            instituteId: Long){
        val groupStudents = Entities.GroupStudents(
                id = id,
                name = name,
                instituteId = instituteId
        )
        viewModelScope.launch {
            try {
                if(id == 0L){
                    repository.addGroupStudents(groupStudents)
                }else{
                    repository.updateGroupStudents(groupStudents)
                }
            }catch (t:Throwable){
                Log.d("TAG", "add group students fail", t)
            }
        }
    }
}