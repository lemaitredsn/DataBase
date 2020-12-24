package ru.lemaitre.databasetest.presentation.kaphedra_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.Entities

class KaphedraDetailsViewModel : ViewModel() {
//    private val repository = UniversityRepository()
//
//    private val listTeachersMutableLiveData = MutableLiveData<List<Entities.Teachers>>()
//
//    val teachers: LiveData<List<Entities.Teachers>>
//        get() = listTeachersMutableLiveData
//
//    fun getTeachers(kaphedraId:Long){
//        viewModelScope.launch {
//            try {
//                val list = repository.getAllTeachers().filter { it.kaphedraId == kaphedraId}
//                listTeachersMutableLiveData.postValue(list)
//            }catch (t:Throwable){
//                listTeachersMutableLiveData.postValue(emptyList())
//                Log.d("TAG", "addTeachers fail KaphedraDetailViewModel", t)
//            }
//        }
//    }

}