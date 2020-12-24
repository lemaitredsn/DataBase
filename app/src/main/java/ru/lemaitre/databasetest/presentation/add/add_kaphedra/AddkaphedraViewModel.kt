package ru.lemaitre.databasetest.presentation.add.add_kaphedra

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.Entities

class AddkaphedraViewModel : ViewModel() {
    private val repository = UniversityRepository()

    val mutableViewModel = MutableLiveData<List<Entities.Kaphedra>>()
    val kaphedraLiveData: LiveData<List<Entities.Kaphedra>>
        get() = mutableViewModel

    fun addKaphedras(
        id: Long,
        name: String,
        universityId: Long
    ) {
        val kaphedra = Entities.Kaphedra(
            id = id,
            name = name,
            universityId = universityId
        )
        viewModelScope.launch {
            try {
                if (id == 0L) {
                    repository.addKaphedra(listOf(kaphedra))
                } else {
                    repository.updateKaphedra(kaphedra)
                }
            } catch (t: Throwable) {
                Log.d("TAG", "addInstitute fail", t)
            }

        }
    }
}