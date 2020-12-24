package ru.lemaitre.databasetest.presentation.add.add_descipline

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lemaitre.databasetest.data.UniversityRepository
import ru.lemaitre.databasetest.data.db.model.Entities

class AddDesciplineViewModel: ViewModel() {
    private val repository = UniversityRepository()

    fun addDescipline(
            id: Long,
            name:String,
            kaphedraId: Long){
        val descipline = Entities.Descipline(
                id = id,
                name = name,
                kaphedraId = kaphedraId
        )
        viewModelScope.launch {
            try {
                if(id == 0L){
                    repository.addDescipline(listOf(descipline))
                }else{
                    repository.updateDescipline(descipline)
                }
            }catch (t:Throwable){
                Log.d("TAG", "addInstitute fail", t)
            }
        }
    }
}