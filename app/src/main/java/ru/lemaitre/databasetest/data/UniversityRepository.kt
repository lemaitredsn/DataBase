package ru.lemaitre.databasetest.data

import ru.lemaitre.databasetest.data.db.Database
import ru.lemaitre.databasetest.data.db.model.Entities
import ru.lemaitre.databasetest.data.db.model.University
import ru.lemaitre.databasetest.data.db.model.UniversityWithInstituties

class UniversityRepository() {

    private val universitiesDao = Database.instance.universityDao()

    suspend fun getAllUniversities(): List<University>{
        return universitiesDao.getAllUniversities()
    }

    suspend fun addUniversity(university: University){
        //TODO добавить валидацию
        universitiesDao.insertUniversity(listOf(university))
    }

    suspend fun removeUniversity(universityId: Long){
        universitiesDao.removeUniversityById(universityId)
    }

    suspend fun getUniversityById(universityId:Long): University?{
        return universitiesDao.getUniversityById(universityId)
    }

    suspend fun updateUniversity(university: University){
        universitiesDao.updateUniversity(university)
    }

    suspend fun getUniversityWithRelation():List<UniversityWithInstituties>{
        return universitiesDao.getAllUniversityWithRelations()
    }


    //TODO Work with Institute
    private val instituteDao = Database.instance.instituteDao()

    suspend fun getAllInstituties(): List<Entities.Institute>{
        return instituteDao.getAlInstituties()
    }

    suspend fun addInstitute(institute: Entities.Institute){
        instituteDao.insertInstitute(listOf(institute))
    }

    suspend fun updateInstitute(institute: Entities.Institute){
        instituteDao.updateInstitute(institute)
    }

    //TODO work with Kaphedra
    private val kaphedraDao = Database.instance.kaphedraDao()

    suspend fun addKaphedra(kaphedras: List<Entities.Kaphedra>){
        kaphedraDao.insertKaphedra(kaphedras)
    }

    suspend fun  updateKaphedra(kaphedra: Entities.Kaphedra){
        kaphedraDao.updateKaphedra(kaphedra)
    }

}