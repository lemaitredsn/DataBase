package ru.lemaitre.databasetest.presentation.add.add_university

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.University

class AddUniversityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UniversityRepository()

    fun addUniversity(
        id: Long,
        name: String,
        address: String,
        avatar: String
    ) {
        val university = University(
            id = id,
            name = name,
            address = address,
            avatar = avatar
        )

        viewModelScope.launch {
            try {
                if (id == 0L) {
                    repository.addUniversity(university)
                } else {
                    repository.updateUniversity(university)
                }
            } catch (t: Throwable) {
                Log.d("TAG", "addUnoversity fail", t)
            }
        }
    }

    private val existingUniversityMutableLiveData = MutableLiveData<University>()

    val existingUserLiveData: LiveData<University>
        get() = existingUniversityMutableLiveData

    fun loadUniversity(id: Long) {
        viewModelScope.launch {
            try {
                val university = repository.getUniversityById(id)
                if (university != null) existingUniversityMutableLiveData.postValue(university)
            } catch (t: Throwable) {
                Log.d("TAG", "init $id fail", t)
            }
        }
    }
}