package ru.lemaitre.databasetest.presentation.list_university

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.University

class UniversitiesViewModel(application: Application) : AndroidViewModel(application) {
    val repository = UniversityRepository()

//    private val universitiesMutableLiveData = MutableLiveData<List<University_>>()
//    val universitiesLD: LiveData<List<University_>>
//        get() = universitiesMutableLiveData
//
//    fun getUniversities(){
//        val univers = repository.getUniversities()
//        universitiesMutableLiveData.postValue(univers)
//    }

    private val universitiesMutableLiveData = MutableLiveData<List<University>>()
    val universitiesLD: LiveData<List<University>>
        get() = universitiesMutableLiveData

    fun getAllUniversities() {
        viewModelScope.launch {
            try {
                universitiesMutableLiveData.postValue(repository.getAllUniversities())
            }catch (t:Throwable){
                universitiesMutableLiveData.postValue(emptyList())
                Log.d("TAG", "get all university  fail ", t)
            }
        }
    }

    fun deleteUniversityById(universityId: Long){
        viewModelScope.launch {
            try {
                repository.removeUniversity(universityId)
            }catch (t:Throwable){
                Log.d("TAG", " deleteUniversity fail", t)
            }

        }
    }

    fun loadList() {
        viewModelScope.launch {
            try {
                universitiesMutableLiveData.postValue(repository.getAllUniversities())
            } catch (t: Throwable) {
                universitiesMutableLiveData.postValue(emptyList())
                Log.d("TAG", "load list fail ", t)
            }
        }
    }

}