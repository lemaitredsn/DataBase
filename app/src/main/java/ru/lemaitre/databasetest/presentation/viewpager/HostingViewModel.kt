package ru.lemaitre.databasetest.presentation.viewpager

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.Entities
import ru.lemaitre.databasetest.data.db.model.University
import ru.lemaitre.databasetest.data.db.model.UniversityWithInstituties

class HostingViewModel(application: Application) : AndroidViewModel(application) {
    val repository = UniversityRepository()

    private val mutableUniversity = MutableLiveData<University>()
    val universityLD: LiveData<University>
        get() = mutableUniversity

    fun getUniverById(id: Long) {
        viewModelScope.launch {
            try {
                mutableUniversity.postValue(repository.getUniversityById(id))
            } catch (t: Throwable) {
                mutableUniversity.postValue(null)
                Log.d("TAG", "HostingView fun getUniverById fail", t)
            }
        }
    }

    private val mutableInstitute = MutableLiveData<List<Entities.Institute>>()
    val instituteLD: LiveData<List<Entities.Institute>>
        get() = mutableInstitute

    fun getAllInstituties() {
        viewModelScope.launch {
            try {
                mutableInstitute.postValue(repository.getAllInstituties())
            } catch (t: Throwable) {
                mutableInstitute.postValue(emptyList())
                Log.d("TAG", "load institute fail", t)
            }
        }
    }

    private val mutableUniversityWithRelations = MutableLiveData<List<UniversityWithInstituties>>()
    val univerWithRel: LiveData<List<UniversityWithInstituties>>
        get() = mutableUniversityWithRelations

    fun getUniversWithRelation() {
        viewModelScope.launch {
            try {
                mutableUniversityWithRelations.postValue(repository.getUniversityWithRelation())
            } catch (t: Throwable) {
                mutableUniversityWithRelations.postValue(emptyList())
                Log.d("TAG", "getUniversityWithRelation - fail", t)
            }
        }
    }

    fun getRelationByIdUniver(id: Long){
        viewModelScope.launch {
            try {

                val list =  repository.getUniversityWithRelation()
                val filterdList = list.filter { it.university.id == id }
                mutableUniversityWithRelations.postValue(filterdList)
            }catch (t:Throwable){
                mutableUniversityWithRelations.postValue(emptyList())
                Log.d("TAG", "gethRelationById - fail", t)
            }
        }
    }

}