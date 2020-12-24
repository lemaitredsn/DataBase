package ru.lemaitre.databasetest.presentation.add.add_institute

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.Entities

class AddInstituteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UniversityRepository()

    private val mutableInstituteLiveData = MutableLiveData<List<Entities.Institute>>()
    val InstituteLiveData: LiveData<List<Entities.Institute>>
        get() = mutableInstituteLiveData

    fun addInstitute(
        id: Long,
        name:String,
        universityId: Long){
        val institute = Entities.Institute(
            id = id,
            name = name,
            universityId = universityId
        )
        viewModelScope.launch {
            try {
                if(id == 0L){
                    repository.addInstitute(institute)
                }else{
                    repository.updateInstitute(institute)
                }
            }catch (t:Throwable){
                Log.d("TAG", "addInstitute fail", t)
            }
        }
    }

}