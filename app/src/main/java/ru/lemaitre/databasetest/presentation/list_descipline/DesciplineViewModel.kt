package ru.lemaitre.databasetest.presentation.list_descipline

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.Entities

class DesciplineViewModel: ViewModel() {
    private val repository = UniversityRepository()

    private val desciplinesMutableLiveData = MutableLiveData<List<Entities.Descipline>>()

    val teachers: LiveData<List<Entities.Descipline>>
        get() = desciplinesMutableLiveData

    fun getDedsciplines(kaphedraId:Long){
        viewModelScope.launch {
            try {
                val list = repository.getAllDescipline().filter { it.kaphedraId == kaphedraId}
                desciplinesMutableLiveData.postValue(list)
            }catch (t:Throwable){
                desciplinesMutableLiveData.postValue(emptyList())
                Log.d("TAG", "addTeachers fail KaphedraDetailViewModel", t)
            }
        }
    }

}