package ru.lemaitre.databasetest.presentation.institute_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.Entities

class InstituteDetailsViewModel : ViewModel() {
    private val repository = UniversityRepository()

    private val listGroupMutableLiveData = MutableLiveData<List<Entities.GroupStudents>>()

    val listGroupLiveData: LiveData<List<Entities.GroupStudents>>
        get() = listGroupMutableLiveData

    fun getGroupStudentByInstituteId(instituteId: Long) {
        viewModelScope.launch {
            try {
                val listAllGroupByInstitute = repository.getAllGroupStudents()
                val filteredById = listAllGroupByInstitute.filter { it.instituteId == instituteId }
                listGroupMutableLiveData.postValue(filteredById)
            } catch (t: Throwable) {
                listGroupMutableLiveData.postValue(emptyList())
                Log.d("TAG", "getGroupStudentById fail", t)
            }
        }
    }

    private val institutemutableLiveData = MutableLiveData<List<Entities.Institute>>()
    val institute: LiveData<List<Entities.Institute>>
        get() = institutemutableLiveData

    fun getInsititute(idInstitute: Long) {
        viewModelScope.launch {
            try {
                val institute = repository.getAllInstituties()
                val filtered = institute.filter { it.id == idInstitute }
                institutemutableLiveData.postValue(filtered)

            } catch (t: Throwable) {
                Log.d("TAG", "fail get institute by id", t)
                institutemutableLiveData.postValue(emptyList())
            }
        }
    }

}